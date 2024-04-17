package com.sdub.pbliga.pbligawebscraper.service;

import com.sdub.pbliga.pbligawebscraper.BaseTest;
import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;
import com.sdub.pbliga.pbligawebscraper.model.datalake.FreeClub;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataPullingServiceTest extends BaseTest {

    @InjectMocks
    private DataPullingService dataPullingService;

    @Mock
    private ScrapingService scrapingService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(dataPullingService,"kafkaSendingEnabled", Boolean.TRUE);
        //MockitoAnnotations.openMocks(this);
    }

    @Test
    public void pullData_nullDataType_thrownException() throws LoaderException {

        when(scrapingService.scrapData(null)).thenThrow(LoaderException.class);
        assertThrows(LoaderException.class, () -> {
            dataPullingService.pullData(null);
        });
    }

    @Test
    public void pullData_unknownDataType_thrownException() throws LoaderException {

        when(scrapingService.scrapData(DataType.Unknown)).thenThrow(LoaderException.class);
        assertThrows(LoaderException.class, () -> {
            dataPullingService.pullData(DataType.Unknown);
        });
    }

    @Test
    public void pullData_FreeClubsDataType_correctData() throws LoaderException {

        List<DataLakeModel> dataElements = getDataLakeModelsForTest();

        doReturn(dataElements).when(scrapingService).scrapData(any());

        verify(scrapingService, times(1)).scrapData(any());
        assertEquals(dataElements.toString(), dataPullingService.pullData(DataType.FreeClubs));
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
