package ru.oks.diplom.SpringOntology.controllers;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.oks.diplom.SpringOntology.dto.TimeDifferenceDTO;

import java.io.IOException;
import java.io.OutputStream;

@RestController
public class OntoController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, my friend";
    }

    @GetMapping("/gettime")
    public TimeDifferenceDTO getTimeDifference(@RequestParam("time") long time) {
        TimeDifferenceDTO timeDifferenceDTO = new TimeDifferenceDTO();
        timeDifferenceDTO.currentTime = System.currentTimeMillis();
        timeDifferenceDTO.timediff = timeDifferenceDTO.currentTime - time;

        return timeDifferenceDTO;
    }

    @GetMapping("/helloOntology")
    public String sayHelloOntology() {

        Model model = ModelFactory.createDefaultModel();
        model.read("house.ttl");
        //systemproper1tiesadvanced
        //C:\Program Files (x86)\Common Files\Oracle\Java\javapath
//обработка запроса
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX a:<http://www.semanticweb.org/dande/ontologies/house#>\n" +
                "SELECT ?Дом ?Комнат ?СанУзлов ?Площадь\n" +
                "WHERE { ?Дом a:КоличествоКомнат ?Комнат.\n" +
                "?Дом a:КоличествоСанУзлов ?СанУзлов.\n" +
                "?Дом a:Площадь ?Площадь.\n" +
                "FILTER(?Комнат>1).\n" +
                "FILTER(?СанУзлов=1).\n" +
                "FILTER(?Площадь>40).\n" +
                "}";
        Query query = QueryFactory.create(queryString);
        // No reasoning
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        System.setProperty("console.encoding", "UTF-8");
        OutputStream output = new OutputStream() {

            private final StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            // Netbeans IDE automatically overrides this toString()
            public String toString() {
                return this.string.toString();
            }
        };

        //System.out.println(ResultSetFormatter.asText(results));

        //return "Hello, my friend";
        return ResultSetFormatter.asText(results);
    }
}
