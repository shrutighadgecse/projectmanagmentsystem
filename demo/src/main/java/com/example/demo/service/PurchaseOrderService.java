package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.model.PurchaseOrderReport;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.PurchaseOrderReportRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.KickoffForm;
import com.example.demo.model.Machine;
import com.example.demo.model.SwapRecord;
import com.example.demo.repository.KickoffFormRepository;
import com.example.demo.repository.MachineRepository;
import com.example.demo.repository.SwapRecordRepository;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository repository;
    @Autowired
    private PurchaseOrderReportRepository reportRepository;
    @Autowired
    private KickoffFormRepository kickoffFormRepository;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private SwapRecordRepository swapRecordRepository;

    public PurchaseOrder save(PurchaseOrder po) throws IllegalArgumentException {
        // Check if CCN already exists before saving
        if (po.getCcn() != null && !po.getCcn().trim().isEmpty()) {
            String ccnTrim = po.getCcn().trim();
            boolean ccnExists = repository.findAll().stream()
                .anyMatch(existing -> existing.getCcn() != null && 
                         existing.getCcn().trim().equalsIgnoreCase(ccnTrim) &&
                         (po.getId() == null || !po.getId().equals(existing.getId())));
            
            if (ccnExists) {
                throw new IllegalArgumentException("CCN already exists: " + ccnTrim);
            }
        }

        try {
            PurchaseOrder saved = repository.save(po);
            // Save to report table as well
            PurchaseOrderReport report = new PurchaseOrderReport();
            report.setRecordNo(po.getRecordNo());
            report.setTimestamp(po.getTimestamp());
            report.setCcn(po.getCcn());
            report.setSalesPerson(po.getSalesPerson());
            report.setPoReferenceNumber(po.getPoReferenceNumber());
            report.setPoDate(po.getPoDate());
            report.setPoDeliveryDate(po.getPoDeliveryDate());
            report.setPoValueLacs(po.getPoValueLacs());
            report.setCustomerName(po.getCustomerName());
            report.setMachineName(po.getMachineName());
            report.setModelName(po.getModelName());
            report.setMachineCategory(po.getMachineCategory());
            report.setOrderType(po.getOrderType());
            report.setCountry(po.getCountry());
            report.setState(po.getState());
            report.setCity(po.getCity());
            report.setAddressLine1(po.getAddressLine1());
            report.setAddressLine2(po.getAddressLine2());
            reportRepository.save(report);
            return saved;
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage() != null && e.getMessage().contains("ccn")) {
                throw new IllegalArgumentException("CCN already exists in database");
            }
            throw e;
        }
    }

    public List<PurchaseOrder> getAll() {
        return repository.findAll();
    }

    public boolean addNewValue(String field, String value) {
        if (value == null || value.trim().isEmpty()) return false;
        final String trimmedValue = value.trim();
        List<PurchaseOrder> all = getAll();
        boolean exists = false;
        switch (field) {
            case "salesPerson":
                exists = all.stream().anyMatch(po -> trimmedValue.equalsIgnoreCase(po.getSalesPerson()));
                break;
            case "customerName":
                exists = all.stream().anyMatch(po -> trimmedValue.equalsIgnoreCase(po.getCustomerName()));
                break;
            case "machineName":
                exists = all.stream().anyMatch(po -> trimmedValue.equalsIgnoreCase(po.getMachineName()));
                break;
            case "machineModel":
            case "modelName":
                exists = all.stream().anyMatch(po -> trimmedValue.equalsIgnoreCase(po.getModelName()));
                break;
            default:
                return false;
        }
        if (exists) return false;
        // Add a dummy PurchaseOrder with only the new value set for the field
        PurchaseOrder po = new PurchaseOrder();
        switch (field) {
            case "salesPerson": po.setSalesPerson(trimmedValue); break;
            case "customerName": po.setCustomerName(trimmedValue); break;
            case "machineName": po.setMachineName(trimmedValue); break;
            case "machineModel":
            case "modelName": po.setModelName(trimmedValue); break;
        }
        save(po);
        return true;
    }

    public List<PurchaseOrder> getReportsByMonth(int year, int month) {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(year, month, 1, 0, 0);
        java.time.LocalDateTime end = start.plusMonths(1);
        return repository.findAll().stream()
            .filter(po -> po.getTimestamp() != null &&
                (po.getTimestamp().isEqual(start) || po.getTimestamp().isAfter(start)) &&
                po.getTimestamp().isBefore(end))
            .toList();
    }

    public List<PurchaseOrder> getReportsByYear(int year) {
        java.time.LocalDateTime start = java.time.LocalDateTime.of(year, 1, 1, 0, 0);
        java.time.LocalDateTime end = start.plusYears(1);
        return repository.findAll().stream()
            .filter(po -> po.getTimestamp() != null &&
                (po.getTimestamp().isEqual(start) || po.getTimestamp().isAfter(start)) &&
                po.getTimestamp().isBefore(end))
            .toList();
    }

    @Transactional
    public void swapCcns(String leftCcnRaw, String rightCcnRaw, String remark) {
        if (leftCcnRaw == null || rightCcnRaw == null) throw new IllegalArgumentException("CCN values required");
        String leftCcn = leftCcnRaw.trim();
        String rightCcn = rightCcnRaw.trim();
        if (leftCcn.equalsIgnoreCase(rightCcn)) throw new IllegalArgumentException("CCNs must be different");

        // Find original POs (before swap)
        PurchaseOrder poLeft = repository.findAll().stream()
            .filter(p -> p.getCcn() != null && p.getCcn().trim().equalsIgnoreCase(leftCcn))
            .findFirst().orElse(null);
        PurchaseOrder poRight = repository.findAll().stream()
            .filter(p -> p.getCcn() != null && p.getCcn().trim().equalsIgnoreCase(rightCcn))
            .findFirst().orElse(null);

        // --- PO swap logic (preserve recordNo) ---
        if (poLeft != null && poRight != null) {
            // swap all PO fields except id and recordNo
            LocalDateTime tLeft = poLeft.getTimestamp();
            String ccnLeft = poLeft.getCcn();
            String salesLeft = poLeft.getSalesPerson();
            String poRefLeft = poLeft.getPoReferenceNumber();
            String poDateLeft = poLeft.getPoDate();
            String poDeliveryLeft = poLeft.getPoDeliveryDate();
            Double poValueLeft = poLeft.getPoValueLacs();
            String custLeft = poLeft.getCustomerName();
            String machineLeft = poLeft.getMachineName();
            String modelLeft = poLeft.getModelName();
            String machineCategoryLeft = poLeft.getMachineCategory();
            String orderTypeLeft = poLeft.getOrderType();
            String countryLeft = poLeft.getCountry();
            String stateLeft = poLeft.getState();
            String cityLeft = poLeft.getCity();
            String addr1Left = poLeft.getAddressLine1();
            String addr2Left = poLeft.getAddressLine2();
            String remarksLeft = poLeft.getRemarks();

            // set left from right
            poLeft.setTimestamp(poRight.getTimestamp());
            poLeft.setCcn(poRight.getCcn());
            poLeft.setSalesPerson(poRight.getSalesPerson());
            poLeft.setPoReferenceNumber(poRight.getPoReferenceNumber());
            poLeft.setPoDate(poRight.getPoDate());
            poLeft.setPoDeliveryDate(poRight.getPoDeliveryDate());
            poLeft.setPoValueLacs(poRight.getPoValueLacs());
            poLeft.setCustomerName(poRight.getCustomerName());
            poLeft.setMachineName(poRight.getMachineName());
            poLeft.setModelName(poRight.getModelName());
            poLeft.setMachineCategory(poRight.getMachineCategory());
            poLeft.setOrderType(poRight.getOrderType());
            poLeft.setCountry(poRight.getCountry());
            poLeft.setState(poRight.getState());
            poLeft.setCity(poRight.getCity());
            poLeft.setAddressLine1(poRight.getAddressLine1());
            poLeft.setAddressLine2(poRight.getAddressLine2());
            poLeft.setRemarks(poRight.getRemarks());

            // set right from temp
            poRight.setTimestamp(tLeft);
            poRight.setCcn(ccnLeft);
            poRight.setSalesPerson(salesLeft);
            poRight.setPoReferenceNumber(poRefLeft);
            poRight.setPoDate(poDateLeft);
            poRight.setPoDeliveryDate(poDeliveryLeft);
            poRight.setPoValueLacs(poValueLeft);
            poRight.setCustomerName(custLeft);
            poRight.setMachineName(machineLeft);
            poRight.setModelName(modelLeft);
            poRight.setMachineCategory(machineCategoryLeft);
            poRight.setOrderType(orderTypeLeft);
            poRight.setCountry(countryLeft);
            poRight.setState(stateLeft);
            poRight.setCity(cityLeft);
            poRight.setAddressLine1(addr1Left);
            poRight.setAddressLine2(addr2Left);
            poRight.setRemarks(remarksLeft);

            repository.save(poLeft);
            repository.save(poRight);
        } else if (poLeft != null) {
            // move left -> right (left becomes right), preserve recordNo
            poLeft.setCcn(rightCcn);
            repository.save(poLeft);
        } else if (poRight != null) {
            // move right -> left
            poRight.setCcn(leftCcn);
            repository.save(poRight);
        }

        // After PO-level changes, fetch current PO values for each CCN to map into Kickoff/Machine
        PurchaseOrder poForLeft = repository.findAll().stream()
            .filter(p -> p.getCcn() != null && p.getCcn().trim().equalsIgnoreCase(leftCcn))
            .findFirst().orElse(null);
        PurchaseOrder poForRight = repository.findAll().stream()
            .filter(p -> p.getCcn() != null && p.getCcn().trim().equalsIgnoreCase(rightCcn))
            .findFirst().orElse(null);

        // helpers: map PO -> Kickoff (only PO-related fields), map PO -> Machine (only PO-related fields)
        java.util.function.BiConsumer<com.example.demo.model.KickoffForm, PurchaseOrder> mapPoToKickoff =
            (k, po) -> {
                if (k == null || po == null) return;
                if (po.getCustomerName() != null) k.setCustomerName(po.getCustomerName());
                if (po.getMachineName() != null) k.setMachineName(po.getMachineName());
                if (po.getModelName() != null) k.setMachineModel(po.getModelName());
                // PO related dates/refs/value if Kickoff has setters
                try { if (po.getPoDeliveryDate() != null) k.setPoDeliveryDate(po.getPoDeliveryDate()); } catch(Exception ignored){}
               
                if (po.getOrderType() != null) k.setOrderType(po.getOrderType());
                if (po.getMachineCategory() != null) k.setMachineCategory(po.getMachineCategory());
                // build address
                StringBuilder addrSb = new StringBuilder();
                if (po.getCountry() != null && !po.getCountry().trim().isEmpty()) addrSb.append(po.getCountry().trim());
                if (po.getState() != null && !po.getState().trim().isEmpty()) { if (addrSb.length()>0) addrSb.append(", "); addrSb.append(po.getState().trim()); }
                if (po.getCity() != null && !po.getCity().trim().isEmpty()) { if (addrSb.length()>0) addrSb.append(", "); addrSb.append(po.getCity().trim()); }
                if (po.getAddressLine1() != null && !po.getAddressLine1().trim().isEmpty()) { if (addrSb.length()>0) addrSb.append(", "); addrSb.append(po.getAddressLine1().trim()); }
                if (po.getAddressLine2() != null && !po.getAddressLine2().trim().isEmpty()) { if (addrSb.length()>0) addrSb.append(", "); addrSb.append(po.getAddressLine2().trim()); }
                if (addrSb.length() > 0) k.setAddress(addrSb.toString());
              
               
            };

        java.util.function.BiConsumer<com.example.demo.model.Machine, PurchaseOrder> mapPoToMachine =
            (m, po) -> {
                if (m == null || po == null) return;
                if (po.getCustomerName() != null) m.setCustomerName(po.getCustomerName());
                if (po.getMachineName() != null) m.setMachineName(po.getMachineName());
                if (po.getModelName() != null) m.setModel(po.getModelName());
                try { if (po.getPoReferenceNumber() != null) m.setPoRefNo(po.getPoReferenceNumber()); } catch(Exception ignored){}
                try { if (po.getPoDate() != null) m.setPoDate(po.getPoDate()); } catch(Exception ignored){}
                try { if (po.getPoDeliveryDate() != null) m.setPoDeliveryDate(po.getPoDeliveryDate()); } catch(Exception ignored){}
                try { if (po.getPoValueLacs() != null) m.setPoValueLacs(po.getPoValueLacs()); } catch(Exception ignored){}
                if (po.getOrderType() != null) m.setOrderType(po.getOrderType());
                if (po.getMachineCategory() != null) m.setMachineCategory(po.getMachineCategory());
                // address
                StringBuilder mAddr = new StringBuilder();
                if (po.getCountry() != null && !po.getCountry().trim().isEmpty()) mAddr.append(po.getCountry().trim());
                if (po.getState() != null && !po.getState().trim().isEmpty()) { if (mAddr.length()>0) mAddr.append(", "); mAddr.append(po.getState().trim()); }
                if (po.getCity() != null && !po.getCity().trim().isEmpty()) { if (mAddr.length()>0) mAddr.append(", "); mAddr.append(po.getCity().trim()); }
                if (po.getAddressLine1() != null && !po.getAddressLine1().trim().isEmpty()) { if (mAddr.length()>0) mAddr.append(", "); mAddr.append(po.getAddressLine1().trim()); }
                if (po.getAddressLine2() != null && !po.getAddressLine2().trim().isEmpty()) { if (mAddr.length()>0) mAddr.append(", "); mAddr.append(po.getAddressLine2().trim()); }
                if (mAddr.length() > 0) m.setAddress(mAddr.toString());
                if (po.getSalesPerson() != null) {
                    try { m.setSalesPerson(po.getSalesPerson()); } catch(Exception ignored){}
                }
            };

        // --- Kickoff swap/move/update ---
        KickoffForm kLeft = kickoffFormRepository.findAll().stream()
            .filter(k -> k.getCcn() != null && k.getCcn().trim().equalsIgnoreCase(leftCcn)).findFirst().orElse(null);
        KickoffForm kRight = kickoffFormRepository.findAll().stream()
            .filter(k -> k.getCcn() != null && k.getCcn().trim().equalsIgnoreCase(rightCcn)).findFirst().orElse(null);

        if (kLeft != null && kRight != null) {
            // swap CCN as well as ONLY PO-related fields between kLeft and kRight
            String temp;
            Integer tempInt;

            // swap ccn
            temp = kLeft.getCcn(); kLeft.setCcn(kRight.getCcn()); kRight.setCcn(temp);

            // customer, machine, model, machineCategory, orderType
            temp = kLeft.getCustomerName(); kLeft.setCustomerName(kRight.getCustomerName()); kRight.setCustomerName(temp);
            temp = kLeft.getMachineName(); kLeft.setMachineName(kRight.getMachineName()); kRight.setMachineName(temp);
            temp = kLeft.getMachineModel(); kLeft.setMachineModel(kRight.getMachineModel()); kRight.setMachineModel(temp);
            temp = kLeft.getMachineCategory(); kLeft.setMachineCategory(kRight.getMachineCategory()); kRight.setMachineCategory(temp);
            temp = kLeft.getOrderType(); kLeft.setOrderType(kRight.getOrderType()); kRight.setOrderType(temp);
            // address
            temp = kLeft.getAddress(); kLeft.setAddress(kRight.getAddress()); kRight.setAddress(temp);
            // members present and CP qtys (if present)
            temp = kLeft.getMembersPresent(); kLeft.setMembersPresent(kRight.getMembersPresent()); kRight.setMembersPresent(temp);
            tempInt = kLeft.getMachineCpQty(); kLeft.setMachineCpQty(kRight.getMachineCpQty()); kRight.setMachineCpQty(tempInt);
            tempInt = kLeft.getMachineOptionalCpQty(); kLeft.setMachineOptionalCpQty(kRight.getMachineOptionalCpQty()); kRight.setMachineOptionalCpQty(tempInt);

            // PO-specific fields in kickoff (guarded swaps)
           
            try {
                temp = kLeft.getPoDeliveryDate(); kLeft.setPoDeliveryDate(kRight.getPoDeliveryDate()); kRight.setPoDeliveryDate(temp);
            } catch(Exception ignored){}
            

            kickoffFormRepository.save(kLeft);
            kickoffFormRepository.save(kRight);
        } else if (kLeft != null) {
            // move left kickoff to right CCN and then update its PO-related fields from current PO for right
            kLeft.setCcn(rightCcn);
            mapPoToKickoff.accept(kLeft, poForRight);
            kickoffFormRepository.save(kLeft);
        } else if (kRight != null) {
            kRight.setCcn(leftCcn);
            mapPoToKickoff.accept(kRight, poForLeft);
            kickoffFormRepository.save(kRight);
        }

        // --- Machine swap/move/update ---
        Machine mLeft = machineRepository.findByCcn(leftCcn).orElse(null);
        Machine mRight = machineRepository.findByCcn(rightCcn).orElse(null);

        if (mLeft != null && mRight != null) {
            String temp;
            Integer icomp;

            // swap ccn first so machine records appear under exchanged CCNs
            temp = mLeft.getCcn(); mLeft.setCcn(mRight.getCcn()); mRight.setCcn(temp);

            // swap PO-related fields
            temp = mLeft.getCustomerName(); mLeft.setCustomerName(mRight.getCustomerName()); mRight.setCustomerName(temp);
            temp = mLeft.getMachineName(); mLeft.setMachineName(mRight.getMachineName()); mRight.setMachineName(temp);
            temp = mLeft.getModel(); mLeft.setModel(mRight.getModel()); mRight.setModel(temp);
            temp = mLeft.getPoRefNo(); mLeft.setPoRefNo(mRight.getPoRefNo()); mRight.setPoRefNo(temp);
            temp = mLeft.getPoDate(); mLeft.setPoDate(mRight.getPoDate()); mRight.setPoDate(temp);
            temp = mLeft.getPoDeliveryDate(); mLeft.setPoDeliveryDate(mRight.getPoDeliveryDate()); mRight.setPoDeliveryDate(temp);
            Double dcomp = mLeft.getPoValueLacs(); mLeft.setPoValueLacs(mRight.getPoValueLacs()); mRight.setPoValueLacs(dcomp);
            temp = mLeft.getSalesPerson(); mLeft.setSalesPerson(mRight.getSalesPerson()); mRight.setSalesPerson(temp);
            temp = mLeft.getOrderType(); mLeft.setOrderType(mRight.getOrderType()); mRight.setOrderType(temp);
            temp = mLeft.getMachineCategory(); mLeft.setMachineCategory(mRight.getMachineCategory()); mRight.setMachineCategory(temp);
            temp = mLeft.getAddress(); mLeft.setAddress(mRight.getAddress()); mRight.setAddress(temp);
            // additional fields that represent PO-related group can be swapped if present
            temp = mLeft.getVenue(); mLeft.setVenue(mRight.getVenue()); mRight.setVenue(temp);
            temp = mLeft.getKomNo(); mLeft.setKomNo(mRight.getKomNo()); mRight.setKomNo(temp);
            temp = mLeft.getMembersPresent(); mLeft.setMembersPresent(mRight.getMembersPresent()); mRight.setMembersPresent(temp);
            icomp = mLeft.getMachineCpQty(); mLeft.setMachineCpQty(mRight.getMachineCpQty()); mRight.setMachineCpQty(icomp);
            icomp = mLeft.getMachineOptionalCpQty(); mLeft.setMachineOptionalCpQty(mRight.getMachineOptionalCpQty()); mRight.setMachineOptionalCpQty(icomp);
            temp = mLeft.getMachineBasics(); mLeft.setMachineBasics(mRight.getMachineBasics()); mRight.setMachineBasics(temp);
            temp = mLeft.getMachineFeatures(); mLeft.setMachineFeatures(mRight.getMachineFeatures()); mRight.setMachineFeatures(temp);
            temp = mLeft.getAdditionalOptions(); mLeft.setAdditionalOptions(mRight.getAdditionalOptions()); mRight.setAdditionalOptions(temp);

            machineRepository.save(mLeft);
            machineRepository.save(mRight);
        } else if (mLeft != null) {
            // move left machine to right CCN and then update its PO-related fields from current PO for right
            mLeft.setCcn(rightCcn);
            mapPoToMachine.accept(mLeft, poForRight);
            machineRepository.save(mLeft);
        } else if (mRight != null) {
            mRight.setCcn(leftCcn);
            mapPoToMachine.accept(mRight, poForLeft);
            machineRepository.save(mRight);
        }

        // Save swap record
        SwapRecord rec = new SwapRecord();
        rec.setLeftCcn(leftCcn);
        rec.setRightCcn(rightCcn);
        rec.setRemark(remark);
        rec.setTimestamp(LocalDateTime.now());
        swapRecordRepository.save(rec);
    }

    public Object getByCcn(String ccn) {
        throw new UnsupportedOperationException("Unimplemented method 'getByCcn'");
    }
}
