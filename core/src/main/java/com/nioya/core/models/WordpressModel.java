package com.did.core.models;


import com.did.core.dto.Entry;
import com.did.core.service.WordpressCallService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.json.JSONArray;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Emrah Onder on 3/14/2019
 * @project moteo
 */
@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WordpressModel {


    List<Entry> ent;
    boolean siteChecker = false;


    @ValueMapValue
    private String targetURL;

    @ValueMapValue
    private String selectType;

    @ValueMapValue
    private int itemCount;

    @PostConstruct
    private void initModel() {
        WordpressCallService wordpressCallService = new WordpressCallService(this.targetURL,this.selectType,this.itemCount);
        this.dataBack = wordpressCallService.getDataBack();
        if(this.dataBack == null){
            this.siteChecker = false;
        }else{
            this.siteChecker = true;
        }
    }


    public List<Entry> getDataBack(){
        return dataBack;
    }
    public boolean getSiteChecker() {
        return this.siteChecker;
    }






}
