package com.mycompany.soapserv;

//Service Implementation
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public String getHelloWorldAsString(String name) throws InvalidPasswordException {
        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");
        String username = "";
        String password = "";

        if (userList != null) {
            //get username
            username = userList.get(0).toString();
        }

        if (passList != null) {
            //get password
            password = passList.get(0).toString();
            if (!"tajne".equals(password)) {
                throw new InvalidPasswordException("Secret password - " + password + " - is invalid! - No greetings for you.");
            }

        }
        System.out.println("new client request foasdr: " + username + "/" + password);
        return "Witaj świecie JAX-WS: " + name;
    }

    @Override
    public List<Product> getProducts() {
        System.out.println("");
        System.out.println("asdasdasdsa");

        this.movieDAO = new JpaMovieDAO();

        RsiMovie m1 = new RsiMovie();
        m1.setTitle("Pirates of the Caribbean: The Curse of the Black Pearl");
        RsiMovie m2 = new RsiMovie();
        m2.setTitle("Lord of the Rings: Return of the King");
        RsiMovie m3 = new RsiMovie();
        m3.setTitle("The Penguins of Madagascar");
        RsiMovie m4 = new RsiMovie();
        m4.setTitle("Hunger Games");

        this.movieDAO.save(m1);
        this.movieDAO.save(m2);
        this.movieDAO.save(m3);
        this.movieDAO.save(m4);

        for (Object m : this.movieDAO.findAllMovies()) {
            System.out.println(m.toString());
        }
        System.out.println("new client request for products");
        Product p1 = new Product("Asus UX331UN", "i7 8550u 13 cali mx150", 3500);
        Product p2 = new Product("SNSV NU133XU", "i5 1234 15 cali gt 1500 ti", 3400);
        List<Product> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        return l;
    }

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
            System.out.println(f.getAbsolutePath()+";\n"+f.getCanonicalPath()+"+\n"+f.getPath());
            File test = new File(f.getPath()+File.separator+"posters"+File.separator+"project.png");
            System.out.println(test.getPath());
//            File image = new File("C:\\Users\\Mateusz\\Pictures\\1609695_630897220285476_1662463206_n.jpg");
            return ImageIO.read(test);
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public void createReservation(RsiReservation rsiReservation) {
        this.reservationDao = new JpaReservationDAO();
        reservationDao.save(rsiReservation);
    }
    
    
}
