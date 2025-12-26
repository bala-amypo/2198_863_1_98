public class LessonServiceImpl {

    private final MicroLessonRepository lessonRepo;
    private final CourseRepository courseRepo;

    public LessonServiceImpl(MicroLessonRepository l, CourseRepository c) {
        this.lessonRepo = l;
        this.courseRepo = c;
    }

    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {
        Course c = courseRepo.findById(courseId).orElseThrow(RuntimeException::new);
        lesson.setCourse(c);
        return lessonRepo.save(lesson);
    }

    public MicroLesson updateLesson(Long id, MicroLesson update) {
        MicroLesson existing = lessonRepo.findById(id).orElseThrow(RuntimeException::new);
        existing.setTitle(update.getTitle());
        existing.setDifficulty(update.getDifficulty());
        existing.setContentType(update.getContentType());
        return lessonRepo.save(existing);
    }

    public List<MicroLesson> findLessonsByFilters(String t, String d, String c) {
        return lessonRepo.findByFilters(t, d, c);
    }

    public MicroLesson getLesson(Long id) {
        return lessonRepo.findById(id).orElseThrow(RuntimeException::new);
    }
}
