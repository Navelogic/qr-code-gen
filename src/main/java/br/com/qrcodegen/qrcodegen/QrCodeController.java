package br.com.qrcodegen.qrcodegen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class QrCodeController {

    private final QrCodeGenService qrCodeGenService;

    @Autowired
    public QrCodeController(QrCodeGenService qrCodeGenService) {
        this.qrCodeGenService = qrCodeGenService;
    }

    @PostMapping("/api/qrcode")
    public ResponseEntity<String> genQRCode(@RequestBody String text) {
        try {
            qrCodeGenService.genQrCode(text);
            return ResponseEntity.ok("QRCode gerado com sucesso");
        } catch (Exception e) {
            log.error("Erro ao gerar QRCode", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar QRCode");
        }
    }
}
