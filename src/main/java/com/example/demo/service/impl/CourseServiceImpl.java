public class RecommendationServiceImpl {

    private final RecommendationRepository repo;
    private final UserRepository userRepo;
    private final MicroLessonRepository lessonRepo;

    public RecommendationServiceImpl(
            RecommendationRepository r, UserRepository u, MicroLessonRepository m) {
        this.repo = r;
        this.userRepo = u;
        this.lessonRepo = m;
    }

    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list = repo.findByUserIdOrderByGeneratedAtDesc(userId);
        if (list.isEmpty()) throw new RuntimeException();
        return list.get(0);
    }
}
