/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentação.ManangedBean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import java.io.File;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;


/**
 *
 * @author BLACKLIST
 */

@ManagedBean(name = "impressaoManagedBean")
@SessionScoped
public class ImpressaoManagedBean {
    
    public void preProcessPDF(Object document, String titulo) throws IOException, BadElementException, DocumentException {


        //cria o documento  
        Document pdf = (Document) document;

        //seta a margin e página, precisa estar antes da abertura do documento, ou seja da linha: pdf.open()  
        //pdf.setMargins(200f, 200f, 200f, 200f);  
        pdf.setPageSize(PageSize.A4);
        pdf.addTitle("Título here brother");

        pdf.open();

        //aqui pega o contexto para formar a url da imagem  
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "resource/img" + File.separator + "timbre.png";


        //cria a imagem e passando a url  
        Image image = Image.getInstance(logo);

        image.scaleAbsolute(500, 100);
        //image.scaleToFit(100, 100);

        //alinha ao centro  
        image.setAlignment(Image.ALIGN_CENTER);

        //adciona a img ao pdf  
        pdf.add(image);

        //adiciona um paragrafo ao pdf, alinha também ao centro  
        Paragraph p = new Paragraph(titulo);
        p.setAlignment("center");
        pdf.add(p);

        Paragraph p2 = new Paragraph("   ");
        p2.setAlignment("center");
        pdf.add(p2);
    }
    
     public void gerarXLS(Object document) {  
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
 
        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }  
    } 
    
    
}
