package tn.esprit.spring.Controller.Forum;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.stereotype.Controller;

@Controller(value = "blogController")
@ELBeanName(value = "blogController")
@Join(path = "/blog", to = "/blog.jsf")
public class BlogController {

}
