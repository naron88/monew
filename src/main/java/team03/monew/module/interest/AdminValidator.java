package team03.monew.module.interest;

import jakarta.servlet.http.HttpSession;
import team03.monew.module.interest.exception.UnauthorizedException;

public class AdminValidator {

  private AdminValidator() {
  }

  // 권한이 관리자가 맞는지 체크
  public static void adminValidator(HttpSession session) {

    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
      throw UnauthorizedException.withRole(role);
    }
  }
}
