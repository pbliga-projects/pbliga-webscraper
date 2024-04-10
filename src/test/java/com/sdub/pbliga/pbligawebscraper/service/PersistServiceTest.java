package com.sdub.pbliga.pbligawebscraper.service;

import com.sdub.pbliga.pbligawebscraper.BaseTest;
import com.sdub.pbliga.pbligawebscraper.converter.PbligaScraperDataConverter;
import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;
import com.sdub.pbliga.pbligawebscraper.model.datalake.FreeClub;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PersistServiceTest extends BaseTest {

    @InjectMocks
    private PersistService persistService;

    @BeforeEach
    public void beforeAll() throws Exception {
        FieldUtils.writeField(persistService, "storagePath", "datastorage", true);
    }

    @Test
    public void save_getCorrectJson() throws LoaderException, IOException, URISyntaxException {

        List<DataLakeModel> dataElements = getDataLakeModelsForTest();

        String json = PbligaScraperDataConverter.convertHtmlToJson(dataElements);
        assertEquals(getResourceFileContent("/response/freeClubs.json"), json);
    }

    private static List<DataLakeModel> getDataLakeModelsForTest() {
        List<DataLakeModel> dataElements = new ArrayList<>();

        FreeClub freeClub1 = new FreeClub();
        freeClub1.setCountry("Албания");
        freeClub1.setName("Флямуртари (Влера)");
        freeClub1.setRank("653");
        freeClub1.setStadiumCapacity("30 000");
        freeClub1.setDivision(2);
        freeClub1.setEuroCupParticipant(false);
        freeClub1.setLigaPlace(8);
        freeClub1.setPower11("95,1");
        freeClub1.setFinance("более 10 млн tlr");
        freeClub1.setPlayersCount(23);
        freeClub1.setPlayersCountUnder21(8);
        freeClub1.setAveragePlayersAge("23,4");

        FreeClub freeClub2 = new FreeClub();
        freeClub2.setCountry("Болгария");
        freeClub2.setName("Локомотив (София)");
        freeClub2.setRank("886");
        freeClub2.setStadiumCapacity("47 000");
        freeClub2.setDivision(1);
        freeClub2.setEuroCupParticipant(false);
        freeClub2.setLigaPlace(12);
        freeClub2.setPower11("94,4");
        freeClub2.setFinance("более 10 млн tlr");
        freeClub2.setPlayersCount(18);
        freeClub2.setPlayersCountUnder21(1);
        freeClub2.setAveragePlayersAge("25,8");

        dataElements.add(freeClub1);
        dataElements.add(freeClub2);
        return dataElements;
    }
}
