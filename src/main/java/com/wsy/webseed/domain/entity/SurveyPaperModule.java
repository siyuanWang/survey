package com.wsy.webseed.domain.entity;

/**
 * Created by wsy on 2016/9/24.
 */
public class SurveyPaperModule {
    private Long id;
    private String name;
    private Long moduleIndex;
    private Long paperId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getModuleIndex() {
        return moduleIndex;
    }

    public void setModuleIndex(Long moduleIndex) {
        this.moduleIndex = moduleIndex;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }
}
