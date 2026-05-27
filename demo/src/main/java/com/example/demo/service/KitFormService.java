package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UpdateFailedException;
import com.example.demo.model.KitForm;
import com.example.demo.model.ReferenceSequences;
import com.example.demo.repository.KitFormRepository;
import com.example.demo.repository.ReferenceSequencesRepository;


@Service
public class KitFormService {
      @Autowired
      private  KitFormRepository kitFormRepository;
     
      private final ReferenceSequencesRepository referenceSequencesRepository;

      private int currentSequence; // Current sequence number
      private String currentReferenceId; // Store the current reference ID
      
     
        
            public KitFormService(KitFormRepository kitFormRepository, ReferenceSequencesRepository referenceSequencesRepository) {
                this.kitFormRepository = kitFormRepository;
                this.referenceSequencesRepository = referenceSequencesRepository;
                loadCurrentSequence(); // Load the current sequence from the database
            }
        
            private void loadCurrentSequence() {
                ReferenceSequences referenceSequences = referenceSequencesRepository.findById(1L).orElse(new ReferenceSequences());
                this.currentSequence = referenceSequences.getSequence();
            }
        
            private void saveCurrentSequence() {
                ReferenceSequences referenceSequences = referenceSequencesRepository.findById(1L)
                        .orElse(new ReferenceSequences());
                referenceSequences.setSequence(currentSequence); // Update the current sequence
                referenceSequencesRepository.save(referenceSequences);
            }
  
      public String generateReferenceId() {
          String prefix = "SAK";
          int year = Year.now().getValue();
          currentReferenceId = "%s-%02d-%d".formatted(prefix, currentSequence, year);
          return currentReferenceId;
      }
  
  

   

    
    public List<String> findSuggestions(String query) {
        List<String> suggestions = kitFormRepository.findByBlcPoContaining(query);
        System.out.println("Suggestions found: " + suggestions);
        return suggestions;
    }
    

    public void saveKitForm(String blcPo) {
        KitForm newKitForm = new KitForm();
        newKitForm.setBlcPo(blcPo);
        kitFormRepository.save(newKitForm);
    }


public int countByStatus(String status) {
    return kitFormRepository.countByStatus(status);
}

public Optional<KitForm> findById(Long id) {
    return kitFormRepository.findById(id);
}
public List<KitForm> findByStatus(String status) {
    return kitFormRepository.findByStatus(status);
}

public void saveKitForm(KitForm kitForm) {
    kitForm.setReferenceId(currentReferenceId); // Set the reference ID

    // Set the completion date if the status is COMPLETE
    if ("COMPLETE".equals(kitForm.getStatus())) {
        kitForm.setCompletionDate(LocalDateTime.now()); // Set current date and time
    }

    kitFormRepository.save(kitForm);
    currentSequence++; // Increment the sequence
    saveCurrentSequence(); // Save the updated sequence to the database
}

  public void updateKitForm(KitForm kitForm) {
    Optional<KitForm> existingKitFormOpt = kitFormRepository.findById(kitForm.getId());
    if (existingKitFormOpt.isPresent()) {
        KitForm existingKitForm = existingKitFormOpt.get();
        
        // Update the fields you want to change
        existingKitForm.setReferenceId(kitForm.getReferenceId());
        existingKitForm.setDateTime(kitForm.getDateTime());
        existingKitForm.setInwardDate(kitForm.getInwardDate());
        existingKitForm.setBlcPo(kitForm.getBlcPo());
        existingKitForm.setBlcPoDate(kitForm.getBlcPoDate());
        existingKitForm.setCcn(kitForm.getCcn());
        existingKitForm.setAssemblyCode(kitForm.getAssemblyCode());
        existingKitForm.setMachineName(kitForm.getMachineName());
        existingKitForm.setStage(kitForm.getStage());
        existingKitForm.setInwardBy(kitForm.getInwardBy());
        existingKitForm.setInwardQty(kitForm.getInwardQty());
        existingKitForm.setDescription(kitForm.getDescription());
        existingKitForm.setStatus(kitForm.getStatus()); // Update the status

        // Set the completion date if the status is COMPLETE
        if ("COMPLETE".equals(kitForm.getStatus())) {
            existingKitForm.setCompletionDate(LocalDateTime.now()); // Set current date and time
        } else {
            existingKitForm.setCompletionDate(null); // Clear the completion date if not COMPLETE
        }

        kitFormRepository.save(existingKitForm); // Save the updated entity
    } else {
        throw new UpdateFailedException("KitForm not found");
    }
}


}
