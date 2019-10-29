package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto saldolla;
    Varasto ylivuotava;
    Varasto negSaldo;
    double vertailuTarkkuus = 0.0001;
    Varasto tyhja;

    @Before
    public void setUp() {
        tyhja = new Varasto(-10);
        varasto = new Varasto(10);
        saldolla = new Varasto(10, 5);
        negSaldo = new Varasto(-10, -5);
        ylivuotava = new Varasto(10, 15);

    }

    @Test
    public void konstruktoriLuoTyhjänVarastonNegatiivisellaArvolla() {
        assertEquals(1, tyhja.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenTilavuudellaJasaldollaPalauttaaTilavuuden() {
        assertEquals(10, saldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenTilavuudellaJasaldollaPalauttaaSaldon() {
        assertEquals(5, saldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenNegatiivisellaTilavuudellaJasaldollaOnTyhjaTilavuus() {
        assertEquals(0, negSaldo.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenNegatiivisellaTilavuudellaJasaldollaOnTyhjaSaldo() {
        assertEquals(0, negSaldo.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonLuominenYlimaarallaPalauttaaTilavuuden() {
        assertEquals(10, ylivuotava.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenLataaminenEiKasvataSaldoa() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liiallinenLataaminenEiMeneYli() {
        varasto.lisaaVarastoon(50);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaNegatiivistaArvoa() {
        double saatu = varasto.otaVarastosta(-5);
        assertEquals(0, saatu, vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaYliSadoa() {
        double saatu = saldolla.otaVarastosta(50);
        assertEquals(5, saatu, vertailuTarkkuus);
    }

    @Test
    public void saldoEiMeneNegatiiviseksi() {
        saldolla.otaVarastosta(50);
        assertEquals(0, saldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringTulostusOikeassaMuodossa() {

        assertEquals("saldo = 5.0, vielä tilaa 5.0", saldolla.toString());
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}