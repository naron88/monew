package team03.monew.config;

//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class AdminInitializer implements ApplicationRunner {
//
//  private final UserRepository userRepository;
//
//  @Value("${admin.email}")
//  private String adminEmail;
//
//  @Value("${admin.password}")
//  private String adminPassword;
//
//  @Value("${admin.nickname}")
//  private String adminNickname;
//
//  @Override
//  public void run(ApplicationArguments args){
//    if (userRepository.findByEmail(adminEmail).isEmpty()) {
//      User admin = new User(adminNickname, adminEmail, adminPassword, User.Role.ADMIN);
//      userRepository.save(admin);
//      log.debug("관리자 계정 생성: {}", admin.getEmail());
//    } else {
//      log.debug("관리자 계정이 이미 있습니다.");
//    }
//  }
//}
