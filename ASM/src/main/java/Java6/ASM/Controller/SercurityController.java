package Java6.ASM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SercurityController {
    @RequestMapping("/sercurity/login/form")
    public String loginForm(Model model) {
        // model.addAttribute("mess", "Vui lòng nhập lại");
        return "sercurity/login";
    }

    @RequestMapping("/sercurity/login/success")
    public String loginSucces(Model model) {
        model.addAttribute("mess", "Đăng nhập thành công");
        return "sercurity/login";
    }

    @RequestMapping("/sercurity/login/error")
    public String loginError(Model model) {
        model.addAttribute("mess", "Sai Thông tin đăng nhập");
        return "sercurity/login";
    }
    @RequestMapping("/sercurity/unauthoried")
    public String unauthoried(Model model){
        model.addAttribute("mess", "Không có quyền truy xuất");
        return "sercurity/login";
    }
    @RequestMapping("/sercurity/logoff/success")
    public String logOfSuccess(Model model){
        model.addAttribute("mess", "Bạn Đã Đăng Xuất");
        return "sercurity/login";
    }
}
