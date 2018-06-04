package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    public static boolean showResults = false;

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<HashMap<String, String>> jobsList = new ArrayList<>();
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("key", searchType);
        model.addAttribute("showResults",showResults);

        showResults = true;

        if (searchType.equals("all")) {
            jobsList = JobData.findByValue(searchTerm);
            model.addAttribute("jobs", jobsList);
            return "search";
        } else {
            jobsList = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobsList);
            return "search";
        }
    }
}
