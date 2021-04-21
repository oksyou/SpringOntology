package ru.oks.diplom.SpringOntology.services;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.lang.sparql_11.ParseException;
import org.springframework.stereotype.Service;
import ru.oks.diplom.SpringOntology.dto.RequestDto;
import ru.oks.diplom.SpringOntology.dto.ResultDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandlingService {
    private Model model;
    private List<ResultDto> results;
    private RequestDto request;
    private QueryExecution queryExecution;

    public List<ResultDto> execute() throws ParseException {
        initialize();
        readModelFromFile();
        prepareQuery();
        getProjectByRequest();

        return results;
    }

    private void initialize() {
        model = ModelFactory.createDefaultModel();
        results = new ArrayList<>();
    }

    private void readModelFromFile() {
        //model = RDFDataMgr.loadModel("house.ttl", Lang.TURTLE);
        //Model model = ModelFactory.createDefaultModel();
        model = model.read("house.ttl");
    }

    private void prepareQuery() throws ParseException {
        prepareQueryBody();
        Query query = QueryFactory.create(prepareQueryBody());
        queryExecution = QueryExecutionFactory.create(query, model);
    }

    private Query prepareQueryBody() throws ParseException {
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX a:<http://www.semanticweb.org/dande/ontologies/house#>\n" +
                "SELECT ?idi ?rooms ?bathrooms ?square\n" +
                "WHERE { " +
                "?home a:Площадь ?square.\n" +
                "?home a:КоличествоКомнат ?rooms.\n" +
                "?home a:КоличествоСанУзлов ?bathrooms.\n" +
                "?home a:КоличествоЭтажей ?floor.\n" +
                "?home a:КомнатаПервыйЭтаж ?firstRoom.\n" +
                "?home a:СмежныеКомнаты ?nearby.\n" +
                "?home a:id ?idi.\n" +
                terrace() +
                garage() +
                buildFilter()
                + " }";

        ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString();
        parameterizedSparqlString.setCommandText(queryString);

        return parameterizedSparqlString.asQuery();
    }

    private String garage() {
        return (request.isGarage()) ? "?home a:имеетГараж ?Гараж.\n" : "";
    }

    private String terrace() {
        return (request.isTerrace()) ? "?home a:имеетТеррасу ?Терраса.\n" : "";
    }

    private String buildFilter() {

        return " FILTER(?rooms>=" + request.getMinRooms() + ").\n" +
                " FILTER(?rooms<=" + request.getMaxRooms() + ").\n" +
                "FILTER(?bathrooms<=" + request.getNumberBathrooms() + ").\n" +
                "FILTER(?square>=" + request.getMinSquare() + ").\n" +
                "FILTER(?square<=" + request.getMaxSquare() + ").\n" +
                "FILTER(?floor=" + request.getNumberFloors() + ").\n" +
                "FILTER(?nearby=" + request.isRoomsNearby() + ").\n" +
                "FILTER(?firstRoom=" + request.isRoomFirstFloor() + ").";
    }

    private void getProjectByRequest() {
        try {
            ResultSet resultSet = queryExecution.execSelect();

            while (resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.nextSolution();
                ResultDto resultDto = prepareResultDto(querySolution);
                results.add(resultDto);
            }
        } finally {
            queryExecution.close();
        }
    }

    private ResultDto prepareResultDto(QuerySolution querySolution) {
        ResultDto returnDto = new ResultDto();

        Literal home=querySolution.getLiteral("idi");
        Literal rooms = querySolution.getLiteral("rooms");
        Literal bathrooms = querySolution.getLiteral("bathrooms");
        Literal square = querySolution.getLiteral("square");

        returnDto.setHouse(home.getInt());
        returnDto.setRooms(rooms.getInt());
        returnDto.setBathrooms(bathrooms.getInt());
        returnDto.setSquare(square.getInt());

        return returnDto;
    }

    public void setRequest(RequestDto request) {
        this.request = request;
    }
}
