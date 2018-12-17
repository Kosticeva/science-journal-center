package com.uns.ftn.sciencejournal.service.utils;

import com.uns.ftn.sciencejournal.model.common.Paper;

import java.time.LocalDateTime;

public class DOIUtils {

    public static String generateDOI(Paper paper) {
        return LocalDateTime.now().toString();
    }
}
