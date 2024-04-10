package com.sdub.pbliga.pbligawebscraper.config;

import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;
import com.sdub.pbliga.pbligawebscraper.model.DataStructureLoaderModel;
import com.sdub.pbliga.pbligawebscraper.model.XpathAndCountPair;
import com.sdub.pbliga.pbligawebscraper.model.datalake.FreeClub;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import com.sdub.pbliga.pbligawebscraper.processor.ResultSetProcessor;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class PbligaPagesLoaderConfig {

    @Bean
    public Map<DataType, DataStructureLoaderModel> getPages() {

        Map<DataType, DataStructureLoaderModel> pages = new HashMap<>();
        pages.put(DataType.FreeClubs, new DataStructureLoaderModel("https://pbliga.com/mng_vacant.php",
                new XpathAndCountPair("//*[@id=\"yt_table1\"]/tbody/tr", true, true),
                getDataProcessors().get(DataType.FreeClubs)
        ));

        return pages;
    }


    @Bean
    public Map<DataType, ResultSetProcessor> getDataProcessors() {
        Map<DataType, ResultSetProcessor> processors = new HashMap<>();

        processors.put(DataType.FreeClubs, (element) -> {
            FreeClub freeClub = new FreeClub();
            freeClub.setCountry(element.findElement(By.xpath("td[1]/img")).getAttribute("Alt"));
            freeClub.setName(element.findElement(By.xpath("td[1]")).getText());
            freeClub.setRank(element.findElement(By.xpath("td[3]")).getText());
            freeClub.setStadiumCapacity(element.findElement(By.xpath("td[4]")).getText());
            String[] division = element.findElement(By.xpath("td[5]")).getText().split(" ");
            freeClub.setDivision(Integer.valueOf(division[0]));
            if (division.length > 1) {
                freeClub.setEuroCupParticipant(true);
            }
            String ligaPlace = element.findElement(By.xpath("td[6]")).getText();
            if (ligaPlace.endsWith("*"))
                ligaPlace = ligaPlace.substring(0,ligaPlace.length()-1);
            freeClub.setLigaPlace(Integer.valueOf(ligaPlace));

            freeClub.setPower11(element.findElement(By.xpath("td[7]")).getText());
            freeClub.setFinance(element.findElement(By.xpath("td[8]/img")).getAttribute("Alt"));

            freeClub.setPlayersCount(Integer.valueOf(element.findElement(By.xpath("td[9]")).getText()));
            freeClub.setPlayersCountUnder21(Integer.valueOf(element.findElement(By.xpath("td[10]")).getText()));
            freeClub.setAveragePlayersAge(element.findElement(By.xpath("td[11]")).getText());

            return freeClub;
        });

        return processors;
    }
}
