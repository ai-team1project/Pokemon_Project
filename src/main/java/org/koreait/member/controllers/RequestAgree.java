package org.koreait.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class RequestAgree {
    @AssertTrue
    private boolean requiredTerms1;
    @AssertTrue
    private boolean requiredTerms2;
    @AssertTrue
    private boolean requiredTerms3;

    private List<String> optionalTerms;
}
