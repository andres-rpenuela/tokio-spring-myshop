package com.tokioschool.myshop.controller;

import com.tokioschool.myshop.domain.Product;
import com.tokioschool.myshop.service.ProductService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Controlador para zonas generales de la aplicación
 */
@Controller
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    private final ProductService productService;

    public WebController(@NonNull ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @PostMapping("/")
    public String index(Model model) {
        String userLoggin = SecurityContextHolder.getContext().getAuthentication().getName();
        final List<String> authorities = new ArrayList<>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .forEach(authority -> authorities.add(authority.getAuthority()));
        logger.info("User logged as: {}, has authorities: {}",userLoggin,authorities);

        Set<Product> products = productService.findAllVisible();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/checkout")
    public String checkout(Model model) {
    	
        return "checkout";
    }
}
