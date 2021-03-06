package xyz.springabc.web.front;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.springabc.domin.Topic;
import xyz.springabc.domin.User;
import xyz.springabc.repository.SectionRepo;
import xyz.springabc.service.NodeServ;
import xyz.springabc.service.TopicServ;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeC {
    Logger logger = Logger.getLogger("webFront");

    @Autowired
    private TopicServ topicServ;

    @Autowired
    private SectionRepo sectionRepo;

    @Autowired
    private NodeServ nodeServ;

    /**
     * 获取所有分类的话题
     *
     * @param p
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1", value = "p") int p,
                        Model model) {
        Page<Topic> topicPage = topicServ.getAllFilteByStatusOrderByDefault(p);
        List<Topic> topics = topicPage.getContent();
        model.addAttribute("topics", topics);
        model.addAttribute("page", topicPage);
        model.addAttribute("sectionName", "all");
        model.addAttribute("sections", sectionRepo.findAll());
        model.addAttribute("hotNodes", nodeServ.getAllOrderByTopicCount(1, 10).getContent());
        logger.debug(11111);
        return "home/index";
    }

    /**
     * 显示首页列表
     *
     * @param p
     * @param section 分类
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "!{section}")
    public String order(@RequestParam(defaultValue = "1", value = "p") int p,
                        @PathVariable(value = "section") String section,
                        HttpServletRequest request,
                        Model model) {
        int userId = 0;//默认没有
        if (request.getSession().getAttribute("user") != null) {
            userId = ((User) request.getSession().getAttribute("user")).getId();
        }
        Page<Topic> topicPage;
        if (section.equals("hot")) {
            topicPage = topicServ.getAllOrderByHot(p);
        } else if (section.equals("focused")) {
            topicPage = topicServ.getByFocusedOrderByDefault(userId, p);
        } else {
            topicPage = topicServ.getBySectionDefault(section, p);
        }
        List<Topic> topics = topicPage.getContent();
        model.addAttribute("topics", topics);
        model.addAttribute("page", topicPage);
        model.addAttribute("sectionName", section);
        model.addAttribute("sections", sectionRepo.findAll());
        model.addAttribute("hotNodes", nodeServ.getAllOrderByTopicCount(1, 10).getContent());
        return "home/index";
    }
}
