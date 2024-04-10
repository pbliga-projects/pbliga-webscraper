package com.sdub.pbliga.pbligawebscraper.config;

import com.sdub.pbliga.pbligawebscraper.messaging.config.KafkaConfig;
import com.sdub.pbliga.pbligawebscraper.messaging.service.KafkaMessagingService;
import com.sdub.pbliga.pbligawebscraper.messaging.service.MessagingService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.net.URL;

@Configuration
@Import(KafkaConfig.class)
public class ApplicationConfig {

    @Value("${data.chromedriver.path}")
    private String chromedriver;

    @Value("${remote.web.driver.url}")
    private String remoteWebDriverURL;

    @Value("${messaging.provider}")
    private String messagingProvider;

    public String getMessagingProvider() {
        return messagingProvider;
    }

    @Bean(destroyMethod = "quit")
    @Lazy
    public WebDriver webDriver() throws IOException {
        WebDriver driver;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-dev-shm-usage");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        options.setCapability(CapabilityType.BROWSER_VERSION, "110.0");
        options.setCapability(CapabilityType.PLATFORM_NAME, "Linux");

        /*System.setProperty("webdriver.chrome.driver", chromedriver);
        driver = new ChromeDriver(options);*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new RemoteWebDriver(new URL(remoteWebDriverURL), capabilities);

        /*WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver(options);*/

        /*WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--private");*/
        /*options.addArguments("--disable-dev-shm-usage");*/
        //driver = new FirefoxDriver(options);
        /*WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();*/
        return driver;
    }

    @Bean
    @ConditionalOnProperty(name = "messaging.provider", havingValue = "kafka")
    public MessagingService kafkaMessagingService(@Value("${messaging.topics.scrapper-data}") String topicName) {
        return new KafkaMessagingService(topicName);
    }

    @Bean
    @ConditionalOnProperty(name = "messaging.provider", havingValue = "none", matchIfMissing = true)
    public MessagingService defaultMessagingService() {
        throw new BeanCreationException("Missing required property 'messaging.provider'");
    }
}
