package io.github.danielgrycman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by danielgrycman
 */
@RestController
@RequestMapping("/layouts/all")
public class AllLayoutController {

    @Autowired
    ResourcePatternResolver resourcePatternResolver;

    @RequestMapping(value = "")
    public List<String> allLayouts() throws IOException {

        Resource[] resources = resourcePatternResolver.getResources("classpath:xml_layouts/*.xml");

        List<String> layouts = null;
        for (Resource resource : resources){
            String fileName = resource.getFilename();
            System.out.println(fileName);
            layouts.add(fileName);

        }
        return layouts;
    }
}
