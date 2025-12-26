public class UserServiceImpl {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository repo, BCryptPasswordEncoder enc, JwtUtil jwt) {
        this.userRepository = repo;
        this.encoder = enc;
        this.jwtUtil = jwt;
    }

    public User register(User user) {
        if (user == null) throw new RuntimeException();
        if (userRepository.existsByEmail(user.getEmail())) throw new RuntimeException();

        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);

        if (!encoder.matches(password, user.getPassword()))
            throw new RuntimeException();

        String token = jwtUtil.generateToken(new HashMap<>(), email);
        return new AuthResponse(token);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
