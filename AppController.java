package com.example.qrcodeform.controller;

import com.example.qrcodeform.util.QRCodeGenerator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Controller
public class AppController {

    
    @GetMapping("/")
    public String homePage(Model model) {योग करें)
        String formUrl = "http://192.168.1"; 
        
        try {
            byte[] qrCodeImage = QRCodeGenerator.getQRCodeImage(formUrl, 250, 250);
            String base64Image = Base64.getEncoder().encodeToString(qrCodeImage);
            
            // फ्रंटएंड HTML में इमेज डेटा भेजना
            model.addAttribute("qrCode", "data:image/png;base64," + base64Image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/user-form")
    public String showForm() {
        return "form";
    }

   
    @PostMapping("/submit-form")
    @ResponseBody
    public String handleFormSubmit(@RequestParam String name, @RequestParam String email) {
        // यहाँ आप डेटाबेस में एंट्री कर सकते हैं
        return "धन्यवाद, " + name + "! आपका फॉर्म सफलतापूर्वक सबमिट हो गया है।";
    }
}

