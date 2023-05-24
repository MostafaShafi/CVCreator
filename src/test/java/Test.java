import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

public class Test {

    private static String FIRST_FILE = "E:/Git Projects/CVCreator/src/main/resources/static/pdf/FirstPdf.pdf";
    private static String SECOND_FILE = "E:/Git Projects/CVCreator/src/main/resources/static/pdf/SecondPdf.pdf";
    private static String profile = "E:/Git Projects/CVCreator/src/main/resources/static/pdf/profile.png";

    public static void main(String[] args) throws DocumentException, IOException {
        Test test = new Test();
        BaseFont baseFont = BaseFont.createFont(
                "E:/Git Projects/CVCreator/src/main/resources/static/font/Aleo-Regular.ttf", BaseFont.WINANSI, false);
        Font aleoFont = new Font(baseFont, 11f, Font.NORMAL);

        test.roundImage("E:/Git Projects/CVCreator/src/main/resources/static/images/profile/profile.png", profile);
        test.createRectangle();
        test.addProfileImage();
        //test.createCircle();
    }

    private void createRectangle()
            throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter firstWriter = PdfWriter.getInstance(document, new FileOutputStream(FIRST_FILE));
        document.open();
        document.newPage();
        PdfContentByte directContent = firstWriter.getDirectContent();
        Rectangle rect = new Rectangle(0f, 0f, 200f, 842f);
        rect.setBackgroundColor(new BaseColor(50,59,76,255));
        directContent.rectangle(rect);
        rect = new Rectangle(40f, 692f, 160f, 812f);
        rect.setBackgroundColor(new BaseColor(50,59,76,255));
        directContent.rectangle(rect);

        document.close();
        firstWriter.close();
    }

    private void addProfileImage() throws DocumentException, IOException {
        PdfReader reader = new PdfReader(FIRST_FILE);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(SECOND_FILE));
        Image mainImage = Image.getInstance(profile);
        mainImage.setAbsolutePosition(40f, 692f);
        mainImage.scaleAbsolute(120f, 120f);
        PdfImage stream = new PdfImage(mainImage, "", null);
        stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
        PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
        mainImage.setDirectReference(ref.getIndirectReference());
        PdfContentByte over = stamper.getOverContent(1);
        over.addImage(mainImage);
        stamper.close();
        reader.close();
    }

    private void roundImage(String srcImage, String finalImage) throws IOException {
        BufferedImage icon = ImageIO.read(
                new File(srcImage));

        int w = icon.getWidth();
        int h = icon.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setColor(new Color(50,59,76,255));
        g2.setBackground(new Color(50,59,76,255));
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, 300, 300));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(icon, 0, 0, null);
        g2.dispose();
        
        ImageIO.write(output, "png",
                new File(finalImage));
    }
}
