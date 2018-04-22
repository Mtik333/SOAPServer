package com.mycompany.soapserv;

//Service Implementation
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.soapserv.db.AuditoriumDAO;
import com.mycompany.soapserv.db.ClientDAO;
import com.mycompany.soapserv.db.JpaAuditoriumDAO;
import com.mycompany.soapserv.db.JpaClientDAO;
import com.mycompany.soapserv.db.JpaMovieDAO;
import com.mycompany.soapserv.db.JpaReservationDAO;
import com.mycompany.soapserv.db.JpaScreeningDAO;
import com.mycompany.soapserv.db.JpaSeatDAO;
import com.mycompany.soapserv.db.JpaSeatReservedDAO;
import com.mycompany.soapserv.db.MovieDAO;
import com.mycompany.soapserv.db.ReservationDAO;
import com.mycompany.soapserv.db.ScreeningDAO;
import com.mycompany.soapserv.db.SeatDAO;
import com.mycompany.soapserv.db.SeatReservedDAO;
import com.mycompany.soapserv.moviedto.RsiAuditorium;
import com.mycompany.soapserv.moviedto.RsiClient;
import com.mycompany.soapserv.moviedto.RsiMovie;
import com.mycompany.soapserv.moviedto.RsiReservation;
import com.mycompany.soapserv.moviedto.RsiScreening;
import com.mycompany.soapserv.moviedto.RsiSeat;
import com.mycompany.soapserv.moviedto.RsiSeatReserved;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

//@WebService(endpointInterface = "com.company.HelloWorld", portName = "test", serviceName = "test", targetNamespace = "test")
@WebService(endpointInterface = "com.mycompany.soapserv.HelloWorld")

public class HelloWorldImpl implements HelloWorld {

    @Resource
    WebServiceContext wsctx;
    private MovieDAO movieDAO;
    private AuditoriumDAO auditoriumDAO;
    private ClientDAO clientDao;
    private ReservationDAO reservationDao;
    private ScreeningDAO screeningDao;
    private SeatDAO seatDao;
    private SeatReservedDAO seatRDao;

    @Override
    public List<RsiAuditorium> getAuditoriums() {
        this.auditoriumDAO = new JpaAuditoriumDAO();
        return auditoriumDAO.findAll();
    }

    @Override
    public Boolean authenticateClient() throws InvalidPasswordException {
        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");
        String username = "";
        String password = "";
        if (userList != null) {
            username = userList.get(0).toString();
        }
        if (passList != null) {
            password = passList.get(0).toString();
            this.clientDao = new JpaClientDAO();
            RsiClient client = clientDao.findByUsernamePassword(username, password);
            if (client == null) {
                throw new InvalidPasswordException("Secret password - " + password + " - is invalid! - No greetings for you.");
            }
        }
        return true;
    }

    @Override
    public List<RsiClient> getClients() {
        this.clientDao = new JpaClientDAO();
        return clientDao.findAll();
    }

    @Override
    public List<RsiMovie> getMovies() {
        this.movieDAO = new JpaMovieDAO();
        return movieDAO.findAllMovies();
    }

    @Override
    public List<RsiReservation> getReservations() {
        this.reservationDao = new JpaReservationDAO();
        return reservationDao.findAll();
    }

    @Override
    public List<RsiScreening> getScreenings() {
        this.screeningDao = new JpaScreeningDAO();
        return screeningDao.findAll();
    }

    @Override
    public List<RsiSeat> getSeats() {
        this.seatDao = new JpaSeatDAO();
        return seatDao.findAll();
    }

    @Override
    public List<RsiSeatReserved> getReservedSeats() {
        this.seatRDao = new JpaSeatReservedDAO();
        return seatRDao.findAll();
    }

    @Override
    public Image downloadImage(String name) {
        try {
            File f = new File(HelloWorldImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            System.out.println(f.getAbsolutePath() + ";\n" + f.getCanonicalPath() + "+\n" + f.getPath());
            File test = new File("D:" + File.separator + "posters" + File.separator + name);
            System.out.println(test.getPath());
            System.out.println(test.getAbsolutePath());
            return ImageIO.read(test.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public void createReservation(RsiReservation reservationId, RsiSeat rsiSeat) {
        this.reservationDao = new JpaReservationDAO();
        reservationId = reservationDao.save(reservationId);
        this.seatRDao = new JpaSeatReservedDAO();
        RsiSeatReserved rsiSeatReserved = new RsiSeatReserved();
        rsiSeatReserved.setReservationId(reservationId);
        rsiSeatReserved.setScreeningId(reservationId.getScreeningId());
        rsiSeatReserved.setSeatId(rsiSeat);
        seatRDao.save(rsiSeatReserved);
    }

    @Override
    public byte[] pdfReservation(RsiReservation reservation) {
        try {
            File file = new File("itext-test.pdf");
            FileOutputStream fileout = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter.getInstance(document, fileout);
            document.addAuthor("RSI Movie");
            document.addTitle("Reservation no. "+reservation.getId());
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Reservation", font);
            Chunk chunk2 = new Chunk("Client: "+reservation.getClientReserverId().getUsername());
            Chunk chunk3 = new Chunk("Movie: "+reservation.getScreeningId().getMovieId().getTitle());
            Chunk chunk4 = new Chunk("Auditorium: "+reservation.getScreeningId().getAuditoriumId().getName());
            Chunk chunk5 = new Chunk("Screening time: "+reservation.getScreeningId().getScreeningStart().toString());
            this.seatRDao = new JpaSeatReservedDAO();
            RsiSeatReserved seatReserved = seatRDao.findByReservationId(reservation);
            Chunk chunk6 = new Chunk("Seat: "+seatReserved.getSeatId().getId());
            try {
                document.add(chunk);
                Paragraph preface = new Paragraph();
                preface.add(new Paragraph(" "));
                document.add(preface);
                document.add(chunk2);
                document.add(preface);
                document.add(chunk3);
                document.add(preface);
                document.add(chunk4);
                document.add(preface);
                document.add(chunk5);
                document.add(preface);
                document.add(chunk6);
            } catch (DocumentException ex) {
                Logger.getLogger(HelloWorldImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.close();
            byte[] data = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(HelloWorldImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void removeReservation(RsiReservation reservationId) {
        this.reservationDao = new JpaReservationDAO();
        this.seatRDao = new JpaSeatReservedDAO();
        RsiSeatReserved seatReserved = seatRDao.findByReservationId(reservationId);
        seatRDao.delete(seatReserved);
        reservationDao.delete(reservationId);
    }

    @Override
    public void changeReservation(RsiReservation reservation, RsiSeat rsiSeat) {
        this.reservationDao = new JpaReservationDAO();
        this.seatRDao = new JpaSeatReservedDAO();
        RsiSeatReserved seatReserved = seatRDao.findByReservationId(reservation);
        seatReserved.setSeatId(rsiSeat);
        seatRDao.update(seatReserved);
    }

}
