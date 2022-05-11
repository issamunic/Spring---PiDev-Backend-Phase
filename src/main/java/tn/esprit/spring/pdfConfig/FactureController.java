package tn.esprit.spring.pdfConfig;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

@RestController
@CrossOrigin
public class FactureController {
	
	@Autowired
	PdfModel pdfModel;
	
	@GetMapping("/exportUsers")
	public void exprotUsersPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter=new SimpleDateFormat("yyy-MM-dd_HH:mm:ss");
		String currentDateTime=dateFormatter.format(new Date());
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=users_"+currentDateTime+".pdf";
		response.setHeader(headerKey, headerValue);
		pdfModel.export(response);
	}
}
