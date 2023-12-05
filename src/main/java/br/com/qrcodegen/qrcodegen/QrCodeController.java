package br.com.qrcodegen.qrcodegen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class QrCodeController {

    @Autowired
    QrCodeGenService qrCodeGenService;

    @PostMapping("/api/qrcode")
    public String genQRCode(@RequestBody String text){
        log.info("Gerando QRCode {}", text);
        qrCodeGenService.genQrCode(text);
        return "QRCode gerado com sucesso";
    }
}
