package br.com.qrcodegen.qrcodegen;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QrCodeGenService {

    @Value("${qrcode.message}")
    private String qrCodeMessage;

    @Value("${qrcode.output.directory}")
    private String outputLocation;

    private static final int QR_CODE_SIZE = 400;
    private static final String DATE_FORMAT = "ddMMyyyyHHmmss";

    public void genQrCode(String text) {
        log.info("Gerando QRCode {}", text);
        log.info("Diretório de saída: {}", outputLocation);

        try {
            String finalText =  text;
            log.info("Texto final: {}", finalText);
            String outputFileName = prepareOutputFileName();
            processarQRCode(finalText, outputFileName);
        } catch (Exception e) {
            log.error("Erro ao gerar QRCode", e);
        }
    }

    private String prepareOutputFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String formattedDateTime = now.format(formatter);
        String fileName = formattedDateTime + ".png";
        return Paths.get(outputLocation, fileName).toString();
    }

    private void processarQRCode(String qrCodeData, String filePath) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
            Path outputPath = Paths.get(filePath);
            MatrixToImageWriter.writeToPath(matrix, "PNG", outputPath);
        } catch (WriterException | IOException e) {
            log.error("Erro ao processar QRCode", e);
        }
    }
}