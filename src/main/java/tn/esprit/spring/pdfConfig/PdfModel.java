package tn.esprit.spring.pdfConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;



@Component
public class PdfModel {

	@Autowired
	UserRepository userRepository;
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font=FontFactory.getFont(FontFactory.TIMES);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("user_id",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("login",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("role",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("domain",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("profession",font));
		table.addCell(cell);
		//table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table) {
		List<User> users=userRepository.findAll();
		for (User user : users) {
			table.addCell(String.valueOf(user.getIdUser()));
			table.addCell(user.getLogin());
			if(user.getRole()!=null) {
				table.addCell(user.getRole()+"");
				switch(String.valueOf(user.getRole())) {
					case "admin": 
						table.addCell("---"); 
						table.addCell("---");
						break;
					case "employe":
						table.addCell("---"); 
						if(user.getProfession()!=null)
						{
							table.addCell(user.getProfession().getName());
						}else {
							table.addCell("---");
						}
						break;
					case "company":
						if(user.getDomain()!=null) {
							table.addCell(user.getDomain().getName());
						}
						else {
							table.addCell("---");
						}
						table.addCell("---");
						break;
				}
			}
			
			
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document=new Document(PageSize.A3);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);//18
		font.setColor(Color.darkGray);
		
		Paragraph p=new Paragraph("list of users",font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table=new PdfPTable(5);//nombre de colone
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f,3.5f,1.5f,1.5f,1.5f});
		table.setSpacingBefore(10);//10
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		document.close();
	}
}
