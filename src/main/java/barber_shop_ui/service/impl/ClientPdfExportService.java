package barber_shop_ui.service.impl;
import barber_shop_ui.entity.ClientEntity;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ClientPdfExportService {

    public static byte[] exportToPdf(List<ClientEntity> clients) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Clientes").setBold().setFontSize(16));

        Table table = new Table(3);
        table.addHeaderCell("ID");
        table.addHeaderCell("Nome");
        table.addHeaderCell("Telefone");

        for (ClientEntity client : clients) {
            table.addCell(client.getId().toString());
            table.addCell(client.getNome());
            table.addCell(client.getPhone());
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }
}