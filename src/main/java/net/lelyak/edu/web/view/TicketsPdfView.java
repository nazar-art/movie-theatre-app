package net.lelyak.edu.web.view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.lelyak.edu.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class TicketsPdfView extends AbstractPdfView {

    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest req, HttpServletResponse resp) throws Exception {

        @SuppressWarnings("unchecked")
        List<Ticket> tickets = (List<Ticket>) model.get("tickets");
        Font fontTitle = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);

        PdfPTable ticketsPdfTable = new PdfPTable(5);
        document.add(new Paragraph("Movie Theatre pdf view", fontTitle));

        ticketsPdfTable.setWidthPercentage(100.0f);
        ticketsPdfTable.setSpacingBefore(10);

        ticketsPdfTable.addCell("#");
        ticketsPdfTable.addCell("User:");
        ticketsPdfTable.addCell("Event name:");
        ticketsPdfTable.addCell("Seat Number:");
        ticketsPdfTable.addCell("Price:");
//		ticketsPdfTable.addCell("realPrice");
//		ticketsPdfTable.addCell("discountStrategy");
//		ticketsPdfTable.addCell("discountAmount");

        for (Ticket ticket : tickets) {
            ticketsPdfTable.addCell(String.valueOf(ticket.getId()));
            ticketsPdfTable.addCell(ticket.getUser().getName());
            ticketsPdfTable.addCell(ticket.getEvent().getName());
            ticketsPdfTable.addCell(ticket.getName());
            ticketsPdfTable.addCell(String.valueOf(ticket.getPrice()));
//			ticketsPdfTable.addCell(String.valueOf(ticket.getRealPrice()));
//			ticketsPdfTable.addCell(ticket.getDiscountStrategy().name());
//			ticketsPdfTable.addCell(String.valueOf(ticket.getDiscountAmount()));
        }

        document.add(ticketsPdfTable);

    }

}