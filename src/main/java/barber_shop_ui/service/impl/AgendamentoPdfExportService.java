package barber_shop_ui.service.impl;

import barber_shop_ui.entity.AgendamentoEntity;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.Document;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class AgendamentoPdfExportService {

    public byte[] exportAgendamentosToPdf(List<AgendamentoEntity> agendamentos) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Título do PDF
        document.add(new Paragraph("Lista de Agendamentos")
                .setBold()
                .setFontSize(16)
                .setMarginBottom(15));

        // Adicionando os agendamentos como texto normal
        for (AgendamentoEntity ag : agendamentos) {
            String agendamentoTexto = String.format(
                    "ID: %s\nCliente: %s\nData: %s\nHora: %s\nServiço: %s\n",
                    ag.getId() != null ? ag.getId().toString() : "-",
                    ag.getCliente() != null ? ag.getCliente() : "-",
                    ag.getData() != null ? ag.getData() : "-",
                    ag.getHora() != null ? ag.getHora() : "-",
                    ag.getServico() != null ? ag.getServico() : "-"
            );

            document.add(new Paragraph(agendamentoTexto).setFontSize(12).setMarginBottom(10));
        }

        document.close();
        return out.toByteArray();
    }
}
