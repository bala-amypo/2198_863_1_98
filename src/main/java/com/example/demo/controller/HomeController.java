@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Application is running successfully";
    }
}
