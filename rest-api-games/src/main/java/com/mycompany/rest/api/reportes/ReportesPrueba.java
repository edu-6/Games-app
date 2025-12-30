/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.reportes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author edu
 */

@Path("reportes")
public class ReportesPrueba {
    public static final String PATH = "/home/edu/IPC-2025";
    @GET
    @Path("report1")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadPdfFile() throws JRException {
        
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("Blank_A4.jasper");
        
        JRDataSource source = new JRBeanCollectionDataSource(this.generarLista());

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        //JasperExportManager.exportReportToPdfFile(printer, PATH + "/" + "report1");
        
        
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(printer); // guardar en memoria

        /*StreamingOutput fileStream = (java.io.OutputStream output) -> {
            try {
                java.nio.file.Path path = Paths.get(PATH + "/" + "report1");
                byte[] data = Files.readAllBytes(path);
                output.write(data);
                output.flush();
            } catch (Exception e) {
                e.printStackTrace();
                throw new WebApplicationException("File Not Found !!");
            }
        };*/

        return Response
                .ok(pdfBytes, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "attachment; filename = report1.pdf")
                .build();
    }
    
    
    public List<Prueba> generarLista(){
        List<Prueba> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lista.add(new Prueba(i,"sipues", "notanto"));
        }
        return lista;
    }
    
    /*
    @GET
    @Path("report2")
    public Response downloadPdfFileReport2() throws JRException {
        
        List<EventAndDetailsDto> list = Arrays.asList(
                new EventAndDetailsDto(
                        "evet-001",
                        "Nombre evt-001",
                        25.50,
                        Arrays.asList(
                                new EventDetailDto("detail1", LocalDate.now(), 15)
                        )
                ),
                new EventAndDetailsDto(
                        "evet-002",
                        "Nombre evt-003",
                        125.50,
                        Arrays.asList(
                                new EventDetailDto("detail2", LocalDate.now(), 22),
                                new EventDetailDto("detail3", LocalDate.now(), 33)
                        )
                ),
                new EventAndDetailsDto(
                        "evet-003",
                        "Nombre evt-004",
                        250.50,
                        Arrays.asList(
                                new EventDetailDto("detail4", LocalDate.now(), 44),
                                new EventDetailDto("detail5", LocalDate.now(), 55),
                                new EventDetailDto("detail6", LocalDate.now(), 66)
                        )
                )
        );
        
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/jgranados/rest/api/app/reports/ReportMaster.jasper");
        
        JRDataSource source = new JRBeanCollectionDataSource(list);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfFile(printer, PATH + "/" + "report2");
        

        StreamingOutput fileStream = (java.io.OutputStream output) -> {
            try {
                java.nio.file.Path path = Paths.get(PATH + "/" + "report2");
                byte[] data = Files.readAllBytes(path);
                output.write(data);
                output.flush();
            } catch (Exception e) {
                throw new WebApplicationException("File Not Found !!");
            }
        };

        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "attachment; filename = report2.pdf")
                .build();
    }

*/
}
