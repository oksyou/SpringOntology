//package ru.oks.diplom.SpringOntology.services;
//
//import org.apache.jena.query.*;
//import org.apache.jena.rdf.model.Literal;
//import org.apache.jena.rdf.model.Model;
//import org.apache.jena.sparql.lang.sparql_11.ParseException;
//import ru.oks.diplom.SpringOntology.dto.OntoDto;
//import ru.oks.diplom.SpringOntology.dto.ResultDto;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OntoRepository {
//    private Model model;
//    private OntoDto ontoDto;
//    private List<ResultDto> results;
//    private QueryExecution queryExecution;
//
//    private void initialize() {
//        results = new ArrayList<>();
//    }
//
//    private void readModelFromFile() {
//       // Model model = ModelFactory.createDefaultModel();
//        model.read("house.ttl");
//        //model = RDFDataMgr.loadModel(Commons.RDF_FILENAME, Lang.RDFXML);
//    }
//
//    private void prepareQuery() throws ParseException {
//        prepareQueryBody();
//        Query query = QueryFactory.create(prepareQueryBody());
//        queryExecution = QueryExecutionFactory.create(query, model);
//    }
//
//    private Query prepareQueryBody() throws ParseException {
//        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
//                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
//                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
//                "PREFIX a:<http://www.semanticweb.org/dande/ontologies/house#>\n" +
//                "SELECT ?deviceName ?memoryNumeric ?screenSizeNumeric ?memoryUnit ?jack ?primaryCameraQuality ?hasDualCamera ?hasDualSim ?isTablet WHERE { ?x feature:device-name ?deviceName ;" +
//                " feature:internal-memory-size-numeric ?memoryNumeric ;" +
//                " feature:screen-size-numeric ?screenSizeNumeric ;" +
//                " feature:internal-memory-unit ?memoryUnit ;" +
//                " feature:music-jack ?jack ;" +
//                " feature:screen-size ?screenSize ;" +
//                " feature:primary-camera-quality ?primaryCameraQuality ;" +
//                " feature:has-dual-camera ?hasDualCamera ;" +
//                " feature:has-dual-sim ?hasDualSim ;" +
//                " feature:is-tablet ?isTablet ;" +
//                " feature:internal-memory-size ?memorySize" +
//                buildFilter()
//                + " }";
//
//        ParameterizedSparqlString parameterizedSparqlString = new ParameterizedSparqlString();
//        parameterizedSparqlString.setCommandText(queryString);
//        parameterizedSparqlString.setLiteral("screenSizeParam", criterias.getScreenSize());
//        parameterizedSparqlString.setLiteral("internalMemorySizeParam", criterias.getInternalMemorySize());
//        parameterizedSparqlString.setLiteral("jackParam", criterias.getMusicJack());
//        parameterizedSparqlString.setLiteral("hasDualCameraParam", criterias.getDualCamera());
//        parameterizedSparqlString.setLiteral("primaryCameraQualityParam", criterias.getPrimaryCameraQuality());
//        parameterizedSparqlString.setLiteral("hasDualSimParam", criterias.getHasDualSim());
//
//        return parameterizedSparqlString.asQuery();
//    }
//
//    private String buildFilter() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(" FILTER ( ");
//        stringBuilder.append("str(?screenSize) = ?screenSizeParam " +
//                "&& str(?memorySize) = ?internalMemorySizeParam " +
//                "&& str(?jack) = ?jackParam" +
//                "&& str(?primaryCameraQuality) = ?primaryCameraQualityParam" +
//                "&& str(?hasDualCamera) = ?hasDualCameraParam" +
//                "&& str(?hasDualSim) = ?hasDualSimParam"
//        );
//        stringBuilder.append(" )");
//
//        return stringBuilder.toString();
//    }
//
//    private void getPhonesByCriteria() {
//        try {
//            ResultSet resultSet = queryExecution.execSelect();
//
//            while(resultSet.hasNext()) {
//                QuerySolution querySolution = resultSet.nextSolution();
//                ResultDeviceDto resultDeviceDto = prepareResultDeviceDto(querySolution);
//                results.add(resultDeviceDto);
//            }
//        } finally {
//            queryExecution.close();
//        }
//    }
//
//    private ResultDeviceDto prepareResultDeviceDto(QuerySolution querySolution) {
//        ResultDeviceDto returnDto = new ResultDeviceDto();
//        Literal deviceName = querySolution.getLiteral("deviceName");
//        Literal memorySize = querySolution.getLiteral("memoryNumeric");
//        Literal screenSize = querySolution.getLiteral("screenSizeNumeric");
//        Literal memoryUnit = querySolution.getLiteral("memoryUnit");
//        Literal musicJack = querySolution.getLiteral("jack");
//        Literal hasDualCamera = querySolution.getLiteral("hasDualCamera");
//        Literal primaryCameraQuality = querySolution.getLiteral("primaryCameraQuality");
//        Literal hasDualSim = querySolution.getLiteral("hasDualSim");
//        Literal isTablet = querySolution.getLiteral("isTablet");
//
//        returnDto.setDeviceName(deviceName.toString());
//        returnDto.setMemorySize(memorySize.toString());
//        returnDto.setScreenSize(screenSize.toString());
//        returnDto.setMemoryUnit(memoryUnit.toString());
//        returnDto.setMusicJack(musicJack.toString());
//        returnDto.setHasDualCamera(hasDualCamera.toString());
//        returnDto.setPrimaryCameraQuality(primaryCameraQuality.toString());
//        returnDto.setHasDualSim(hasDualSim.toString());
//        returnDto.setIsTablet(isTablet.toString());
//
//        return returnDto;
//    }
//
//    public void setCriterias(SurveyDto criterias) {
//        this.criterias = criterias;
//    }
//}
