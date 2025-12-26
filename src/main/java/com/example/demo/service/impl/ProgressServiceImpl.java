public class ProgressServiceImpl {

    private final ProgressRepository progressRepo;
    private final UserRepository userRepo;
    private final MicroLessonRepository lessonRepo;

    public ProgressServiceImpl(ProgressRepository p, UserRepository u, MicroLessonRepository m) {
        this.progressRepo = p;
        this.userRepo = u;
        this.lessonRepo = m;
    }

    public Progress recordProgress(Long userId, Long lessonId, Progress incoming) {
        User user = userRepo.findById(userId).orElseThrow(RuntimeException::new);
        MicroLesson lesson = lessonRepo.findById(lessonId).orElseThrow(RuntimeException::new);

        Optional<Progress> existing =
                progressRepo.findByUserIdAndMicroLessonId(userId, lessonId);

        Progress p = existing.orElseGet(Progress::new);
        p.setUser(user);
        p.setMicroLesson(lesson);
        p.setStatus(incoming.getStatus());
        p.setProgressPercent(incoming.getProgressPercent());
        p.setScore(incoming.getScore());

        return progressRepo.save(p);
    }

    public List<Progress> getUserProgress(Long userId) {
        return progressRepo.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
