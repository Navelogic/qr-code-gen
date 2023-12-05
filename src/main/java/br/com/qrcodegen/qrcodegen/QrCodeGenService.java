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
import com.google.zxing.common.StringUtils;

@Service
public class QrCodeGenService {

    @Value("${qrcode.output.dictory}")
    private String outputDictory;

    private static final String charset = "UTF-8";
    private static final String strDateFormat = "ddMMyyyyHHmmss";

    public void genQrCode(String text) {
        System.out.println("Gerando QR Code...");
        System.out.println("Diretório de saída: " + outputDictory);

        try{
            String finalMessage = (StringUtils.isNotBlank(text))?text:"";
            System.out.println("Texto digitado: " + finalMessage);
            processarQRCode(finalMessage, prepareOutputFileName(), charset, 400, 400);
        } catch (WriterException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String prepareOutputFileName() {
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);

        StringBuilder sb = new StringBuilder();
        sb.append(outputDictory).append("\\").append("QRCode-").append(formattedDate).append(".png");
        System.out.println("Nome do arquivo de saída: " + sb.toString());
        return sb.toString();
    }

    private void processarQRCode(String data, String filePath, String charset, int qrCodeheight, int qrCodewidth) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new java.io.File(filePath));
    }
}
