import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    private static String FIRST_FILE = "E:/Git Projects/CVCreator/src/main/resources/static/pdf/FirstPdf.pdf";
    private static String SECOND_FILE = "E:/Git Projects/CVCreator/src/main/resources/static/pdf/SecondPdf.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
        Test test = new Test();

        BaseFont baseFont = BaseFont.createFont(
                "E:/Git Projects/CVCreator/src/main/resources/static/font/Aleo-Regular.ttf", BaseFont.WINANSI, false);
        Font aleoFont = new Font(baseFont, 11f, Font.NORMAL);

        Document document = new Document();
        PdfWriter firstWriter = PdfWriter.getInstance(document, new FileOutputStream(FIRST_FILE));
        PdfWriter secondWriter = PdfWriter.getInstance(document, new FileOutputStream(SECOND_FILE));
        document.open();
        document.newPage();

        test.createRectangle(firstWriter);
        test.createCircle(secondWriter);
        document.close();

        test.addProfileImage();
        firstWriter.close();
    }

    private void createRectangle(PdfWriter writer) {
        PdfContentByte directContent = writer.getDirectContent();
        Rectangle rect = new Rectangle(0, 0, 200, 842);
        rect.setBackgroundColor(new BaseColor(50,59,76,255));
        directContent.rectangle(rect);
    }

    private void addProfileImage() throws DocumentException, IOException {
        PdfReader reader = new PdfReader(FIRST_FILE);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(SECOND_FILE));
        Image image = Image.getInstance("E:/Git Projects/CVCreator/src/main/resources/static/images/profile/profile.jpg");
        image.setAbsolutePosition(40f, 692f);
        image.scaleAbsolute(120f, 120f);
        PdfImage stream = new PdfImage(image, "", null);
        stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
        PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
        image.setDirectReference(ref.getIndirectReference());
        PdfContentByte over = stamper.getOverContent(1);
        over.addImage(image);
        stamper.close();
        reader.close();
    }

    private void createCircle(PdfWriter writer) {
        PdfContentByte cb = writer.getDirectContent();
        cb.setRGBColorFill(0xFF, 0xFF, 0xFF);
        /*BaseColor colorval = new BaseColor(102,178,255);
        cb.setColorStroke(colorval);*/
        cb.circle(100f, 752f, 60.0f);
        cb.stroke();
    }
}
