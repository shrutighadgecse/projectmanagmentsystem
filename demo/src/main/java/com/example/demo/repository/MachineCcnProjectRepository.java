package com.example.demo.repository;

import com.example.demo.model.MachineCcnProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MachineCcnProjectRepository extends JpaRepository<MachineCcnProject, Long> {
    Optional<MachineCcnProject> findByCcn(String ccn);
    Optional<MachineCcnProject> findByCcnIgnoreCase(String ccn);
    
    // Count by ccnStatus and machineCategory (case-insensitive)
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE LOWER(m.ccnStatus) = LOWER(:ccnStatus) AND LOWER(m.machineCategory) = LOWER(:machineCategory)")
    long countByCcnStatusAndMachineCategory(@Param("ccnStatus") String ccnStatus, @Param("machineCategory") String machineCategory);

    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE (m.actualIfatStdCpDateFinal = true AND m.actualIfatStdCpDate BETWEEN :yearStart AND :yearEnd) OR (m.actualIfatCustomerCpDateFinal = true AND m.actualIfatCustomerCpDate BETWEEN :yearStart AND :yearEnd)")
    long countIfatDoneByYear(@Param("yearStart") LocalDate yearStart, @Param("yearEnd") LocalDate yearEnd);

    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.actualIfatStdCpDateFinal = true OR m.actualIfatCustomerCpDateFinal = true")
    long countByIfatFinalDateTrue();

    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.actualCfatDateFinal = true AND m.actualCfatDate BETWEEN :yearStart AND :yearEnd")
    long countCfatDoneByYear(@Param("yearStart") LocalDate yearStart, @Param("yearEnd") LocalDate yearEnd);

    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.actualCfatDateFinal = true")
    long countByCfatFinalDateTrue();

    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.actualHandedToDispatchDateFinal = true AND m.actualHandedToDispatchDate BETWEEN :yearStart AND :yearEnd")
    long countAwaitingClearanceByYear(@Param("yearStart") LocalDate yearStart, @Param("yearEnd") LocalDate yearEnd);

    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.actualHandedToDispatchDateFinal = true")
    long countByActualHandedToDispatchDateFinalTrue();
    
    // Count by machineCategory regardless of status (case-insensitive)
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE LOWER(m.machineCategory) = LOWER(:machineCategory)")
    long countByMachineCategory(@Param("machineCategory") String machineCategory);
    
    // Count by ccnStatus regardless of category (case-insensitive)
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE LOWER(m.ccnStatus) = LOWER(:ccnStatus)")
    long countByCcnStatus(@Param("ccnStatus") String ccnStatus);
    
    // Find all CCNs by status and category (case-insensitive)
    @Query("SELECT m.ccn FROM MachineCcnProject m WHERE LOWER(m.ccnStatus) = LOWER(:ccnStatus) AND LOWER(m.machineCategory) = LOWER(:machineCategory)")
    List<String> findCcnsByStatusAndCategory(@Param("ccnStatus") String ccnStatus, @Param("machineCategory") String machineCategory);

    @Query("SELECT DISTINCT FUNCTION('YEAR', m.actualAssyStartDate) FROM MachineCcnProject m WHERE m.actualAssyStartDate IS NOT NULL ORDER BY FUNCTION('YEAR', m.actualAssyStartDate) DESC")
    List<Integer> findDistinctYearsByActualAssyStartDate();

    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE LOWER(m.ccnStatus) = LOWER(:ccnStatus) AND LOWER(m.machineCategory) = LOWER(:machineCategory) AND (:yearStart IS NULL OR m.actualAssyStartDate BETWEEN :yearStart AND :yearEnd)")
    long countByStatusAndCategoryAndActualAssyStartDateBetween(
            @Param("ccnStatus") String ccnStatus,
            @Param("machineCategory") String machineCategory,
            @Param("yearStart") LocalDate yearStart,
            @Param("yearEnd") LocalDate yearEnd);

    @Query("SELECT m.ccn FROM MachineCcnProject m WHERE LOWER(m.ccnStatus) = LOWER(:ccnStatus) AND LOWER(m.machineCategory) = LOWER(:machineCategory) AND (:yearStart IS NULL OR m.actualAssyStartDate BETWEEN :yearStart AND :yearEnd)")
    List<String> findCcnsByStatusAndCategoryAndActualAssyStartDateBetween(
            @Param("ccnStatus") String ccnStatus,
            @Param("machineCategory") String machineCategory,
            @Param("yearStart") LocalDate yearStart,
            @Param("yearEnd") LocalDate yearEnd);
    
    // Get all distinct machine categories
    @Query("SELECT DISTINCT m.machineCategory FROM MachineCcnProject m WHERE m.machineCategory IS NOT NULL ORDER BY m.machineCategory")
    List<String> findDistinctMachineCategories();
    
    // Count machines with Assy Start Date < filter year (Carry Forward)
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE LOWER(m.machineCategory) = LOWER(:machineCategory) AND m.actualAssyStartDate < :filterYear")
    long countCarryForwardByMachine(@Param("machineCategory") String machineCategory, @Param("filterYear") LocalDate filterYear);
    
    // Count machines with Assy Start Date = filter year (Machine in Build)
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE LOWER(m.machineCategory) = LOWER(:machineCategory) AND YEAR(m.actualAssyStartDate) = YEAR(:filterYear)")
    long countMachineInBuildByMachine(@Param("machineCategory") String machineCategory, @Param("filterYear") LocalDate filterYear);
    
    // Count machines with Invoice Date >= filter year (Dispatch)
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE LOWER(m.machineCategory) = LOWER(:machineCategory) AND m.invoiceDate >= :filterYear")
    long countDispatchByMachine(@Param("machineCategory") String machineCategory, @Param("filterYear") LocalDate filterYear);

    // Manufacturing data: count CCNs with actualAssyStartDateFinal = true per month for a given year
    @Query("SELECT MONTH(m.actualAssyStartDate) as month, COUNT(m) as count FROM MachineCcnProject m WHERE m.actualAssyStartDateFinal = true AND YEAR(m.actualAssyStartDate) = :year GROUP BY MONTH(m.actualAssyStartDate) ORDER BY MONTH(m.actualAssyStartDate)")
    List<Object[]> getManufacturingCountsByMonth(@Param("year") int year);

    // Dispatch data: count CCNs with invoiceDate per month for a given year
    @Query("SELECT MONTH(m.invoiceDate) as month, COUNT(m) as count FROM MachineCcnProject m WHERE m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = :year GROUP BY MONTH(m.invoiceDate) ORDER BY MONTH(m.invoiceDate)")
    List<Object[]> getDispatchCountsByMonth(@Param("year") int year);

    @Query("SELECT MONTH(m.actualDispatchDate) as month, COUNT(m) as count FROM MachineCcnProject m WHERE m.actualDispatchDate IS NOT NULL AND YEAR(m.actualDispatchDate) = :year GROUP BY MONTH(m.actualDispatchDate) ORDER BY MONTH(m.actualDispatchDate)")
    List<Object[]> getActualDispatchCountsByMonth(@Param("year") int year);

    @Query("SELECT MONTH(m.invoiceDate) as month, COUNT(m) as count FROM MachineCcnProject m WHERE m.actualDispatchDateFinal = true AND m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = :year GROUP BY MONTH(m.invoiceDate) ORDER BY MONTH(m.invoiceDate)")
    List<Object[]> getFinalActualDispatchCountsByMonth(@Param("year") int year);

    // Manufacturing details: list of CCNs with actualAssyStartDateFinal in a specific month/year
    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualAssyStartDateFinal = true AND m.actualAssyStartDate IS NOT NULL AND YEAR(m.actualAssyStartDate) = :year AND MONTH(m.actualAssyStartDate) = :month ORDER BY m.actualAssyStartDate ASC")
    List<MachineCcnProject> findManufacturingRecordsByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualAssyStartDateFinal = true AND m.actualAssyStartDate IS NOT NULL AND YEAR(m.actualAssyStartDate) = :year ORDER BY m.actualAssyStartDate ASC")
    List<MachineCcnProject> findManufacturingRecordsByYear(@Param("year") int year);

    // Dispatch details: list of CCNs with invoiceDate in a specific month/year
    @Query("SELECT m FROM MachineCcnProject m WHERE m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = :year AND MONTH(m.invoiceDate) = :month ORDER BY m.invoiceDate ASC")
    List<MachineCcnProject> findDispatchRecordsByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = :year ORDER BY m.invoiceDate ASC")
    List<MachineCcnProject> findDispatchRecordsByYear(@Param("year") int year);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualAssyStartDateFinal = true AND m.actualAssyStartDate < :yearStart AND ((m.invoiceDate IS NULL AND LOWER(m.ccnStatus) = 'live') OR (m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = YEAR(:yearStart))) ORDER BY m.machineName, m.actualAssyStartDate")
    List<MachineCcnProject> findCarryForwardRecordsByYear(@Param("yearStart") LocalDate yearStart);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualAssyStartDateFinal = true AND YEAR(m.actualAssyStartDate) = YEAR(:yearStart) ORDER BY m.machineName, m.actualAssyStartDate")
    List<MachineCcnProject> findMachineInBuildRecordsByYear(@Param("yearStart") LocalDate yearStart);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = YEAR(:yearStart) ORDER BY m.machineName, m.invoiceDate")
    List<MachineCcnProject> findDispatchRecordsByYearStart(@Param("yearStart") LocalDate yearStart);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualDispatchDate IS NOT NULL AND YEAR(m.actualDispatchDate) = :year AND MONTH(m.actualDispatchDate) = :month ORDER BY m.actualDispatchDate ASC")
    List<MachineCcnProject> findDynamicDispatchRecordsByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualDispatchDate IS NOT NULL AND YEAR(m.actualDispatchDate) = :year ORDER BY m.actualDispatchDate ASC")
    List<MachineCcnProject> findDynamicDispatchRecordsByYear(@Param("year") int year);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualDispatchDateFinal = true AND m.actualDispatchDate IS NOT NULL AND YEAR(m.actualDispatchDate) = :year AND MONTH(m.actualDispatchDate) = :month ORDER BY m.actualDispatchDate ASC")
    List<MachineCcnProject> findFinalDispatchRecordsByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT m FROM MachineCcnProject m WHERE m.actualDispatchDateFinal = true AND m.actualDispatchDate IS NOT NULL AND YEAR(m.actualDispatchDate) = :year ORDER BY m.actualDispatchDate ASC")
    List<MachineCcnProject> findFinalDispatchRecordsByYear(@Param("year") int year);

    // Get all distinct machine names from live CCN
    @Query("SELECT DISTINCT m.machineName FROM MachineCcnProject m WHERE m.machineName IS NOT NULL ORDER BY m.machineName")
    List<String> findDistinctMachineNamesByLiveStatus();

    // Count Carry Forward: CCNs with Assy Start Date < filter year
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.machineName = :machineName AND m.actualAssyStartDateFinal = true AND m.actualAssyStartDate < :filterYear AND ((m.invoiceDate IS NULL AND LOWER(m.ccnStatus) = 'live') OR (m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = YEAR(:filterYear)))")
    long countCarryForwardByMachineNameAndYear(@Param("machineName") String machineName, @Param("filterYear") LocalDate filterYear);

    // Count Machine in Build: CCNs with Assy Start Date = filter year (actualAssyStartDateFinal = true)
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.machineName = :machineName AND m.actualAssyStartDateFinal = true AND YEAR(m.actualAssyStartDate) = YEAR(:filterYear)")
    long countMachineInBuildByMachineNameAndYear(@Param("machineName") String machineName, @Param("filterYear") LocalDate filterYear);

    // Count Dispatch: CCNs with Invoice Date in the selected year
    @Query("SELECT COUNT(m) FROM MachineCcnProject m WHERE m.machineName = :machineName AND m.invoiceDate IS NOT NULL AND YEAR(m.invoiceDate) = YEAR(:filterYear)")
    long countDispatchByMachineNameAndYear(@Param("machineName") String machineName, @Param("filterYear") LocalDate filterYear);

    // =====================================================================
    // BALANCE WIP QUERIES (NEW)
    // Balance WIP = CCNs with assembly started (C/F or Machine-in-Build)
    //               that have NOT been dispatched (invoiced) in the selected year
    // Logic: actualAssyStartDateFinal=true AND actualAssyStartDate <= yearEnd
    //        AND (invoiceDate IS NULL OR YEAR(invoiceDate) != selectedYear)
    // =====================================================================

    /**
     * Fetch all Balance WIP records for a given year across all machines.
     *
     * Logic:
     *   - Assembly formally started (actualAssyStartDateFinal = true)
     *   - Started on or before end of selected year (C/F + current year builds)
     *   - CCN is still LIVE  (ccnStatus = 'live')
     *   - Never dispatched   (invoiceDate IS NULL)
     *
     * This gives exactly: (C/F + Machine-in-Build) MINUS dispatched = remaining live CCNs
     */
    @Query("SELECT m FROM MachineCcnProject m " +
           "WHERE m.actualAssyStartDateFinal = true " +
           "  AND m.actualAssyStartDate IS NOT NULL " +
           "  AND m.actualAssyStartDate <= :yearEnd " +
           "  AND LOWER(m.ccnStatus) = 'live' " +
           "  AND m.invoiceDate IS NULL " +
           "ORDER BY m.machineName, m.actualAssyStartDate")
    List<MachineCcnProject> findBalanceWipRecordsByYear(
            @Param("yearStart") LocalDate yearStart,
            @Param("yearEnd") LocalDate yearEnd);

    /**
     * Count Balance WIP per machine name for a given year.
     *
     * Same logic as above — live CCNs with assembly started but not dispatched.
     */
    @Query("SELECT COUNT(m) FROM MachineCcnProject m " +
           "WHERE m.machineName = :machineName " +
           "  AND m.actualAssyStartDateFinal = true " +
           "  AND m.actualAssyStartDate IS NOT NULL " +
           "  AND m.actualAssyStartDate <= :yearEnd " +
           "  AND LOWER(m.ccnStatus) = 'live' " +
           "  AND m.invoiceDate IS NULL")
    long countBalanceWipByMachineNameAndYear(
            @Param("machineName") String machineName,
            @Param("yearStart") LocalDate yearStart,
            @Param("yearEnd") LocalDate yearEnd);
}