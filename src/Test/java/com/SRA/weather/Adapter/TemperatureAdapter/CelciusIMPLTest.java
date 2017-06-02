package com.SRA.weather.Adapter.TemperatureAdapter;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CelciusIMPLTest {


    @Test
    public void Testconvertfarenheittocelcius() throws Exception {

        FarenheiteIMPL farenheite = EasyMock.createMock(FarenheiteIMPL.class);

        EasyMock.expect(farenheite.getTemperature(EasyMock.anyFloat())).andReturn((float) 75);

        EasyMock.replay(farenheite);


        CelciusIMPL c = new CelciusIMPL(farenheite);

        float t = c.getTemperature(75);

        assertEquals(23.888, t,0.001);

        EasyMock.verify(farenheite);

    }

}