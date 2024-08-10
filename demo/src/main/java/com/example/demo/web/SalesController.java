package com.example.demo.web;

import com.example.demo.entities.Sales;
import com.example.demo.repositories.SalesRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes({"a","e"})
@Controller
@AllArgsConstructor
//This class is responsible for HTTP requests related to the sales records data
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;
    static int num = 0;

    @GetMapping(path = "/index")

    public String sales(Model model) {

        List<Sales> sales = salesRepository.findAll();

        model.addAttribute("listSales", sales);

        return "sales";
}

    @GetMapping("/delete")
    public String delete(Long id) { //@RequestParam
        salesRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/editSales")
    public String editSales(Model model, Long id, HttpSession session) {
        num = 2;
        session.setAttribute("info", 0);
        Sales sales = salesRepository.findById(id).orElse(null);
        if (sales == null) throw new RuntimeException("Sales record does not exist");
        model.addAttribute("sales", sales);
        return "editSales";
    }

    @PostMapping(path = "/save")
    public String save(Model model, Sales sales, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "addCustomers";
        } else {
            salesRepository.save(sales);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
                //session.setAttribute("a", 1);
            }
            return "redirect:index";
        }
    }


}
