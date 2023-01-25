package br.com.totvs.reportgenerator.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.totvs.reportgenerator.model.DeliveryTimeStatusEnum;
import br.com.totvs.reportgenerator.service.ReportService;
import br.com.totvs.reportgenerator.vo.ReportVO;

@Controller
@RequestMapping("/reports")
public class ReportController {

    public static final int pageNumbers = 1;
    public static final int sizeOfPages = 20;



    @Autowired
    ReportService reportService;

    @GetMapping({"", "/", "/{deliveryTimeStatusFilter}"})
    @Cacheable("reportsInTime")
    public String reportsInTime(
            Model model,
            @PathVariable(required = false) String deliveryTimeStatusFilter,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(pageNumbers);
        int pageSize = size.orElse(sizeOfPages);
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        DeliveryTimeStatusEnum deliveryTimeStatus = deliveryTimeStatusFilter == null
                || deliveryTimeStatusFilter.isEmpty()
                ? null : DeliveryTimeStatusEnum.valueOf(deliveryTimeStatusFilter.toUpperCase());

        Page<ReportVO> reportPage = reportService.findPaginated(pageable, deliveryTimeStatus);
        model.addAttribute("reportPage", reportPage);
        model.addAttribute("deliveryTimeStatusFilter", deliveryTimeStatus);
        model.addAttribute("currentPage", currentPage);

        int totalPages = reportPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "reports";
    }
}
