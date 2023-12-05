package br.com.qrcodegen.qrcodegen;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    private static final String charset = "UTF-8";
    private static final String strDateFormat = "ddMMyyyyHHmmss";

    public void genQrCode(String text) {
        log.info("Gerando QRCode {}", text);
        log.info("Diretório de saída: {}", outputLocation);

        try {
            String finalText = qrCodeMessage + text;
            log.info("Texto final: {}", finalText);
            processarQRCode(finalText, prepareOutputFileName(), charset, 400, 400);
        } catch (Exception e) {
            log.error("Erro ao gerar QRCode", e);
        }
    }

    private String prepareOutputFileName() {
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        Date date = new Date(System.currentTimeMillis());
        return outputLocation + dateFormat.format(date) + ".png";
    }

    private void processarQRCode(String qrCodeData, String filePath, String charset, int qrCodeheight, int qrCodewidth)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new java.io.File(filePath));
    }
}