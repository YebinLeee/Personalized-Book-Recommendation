package hello.hellospring.service;

import hello.hellospring.controller.BookSearchQuery;
import hello.hellospring.controller.BookSearchQueryParams;
import hello.hellospring.controller.BookSearchedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookSearchService {

    private final RestTemplate restTemplate;

    @Autowired
    public BookSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookSearchedResult> searchBooks(BookSearchQuery query) {

        BookSearchQueryParams params = new BookSearchQueryParams();
        params.setQuery(query.getQuery());

        System.out.println("params = " + params);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx")
                .queryParam("ttbkey", params.getKey())
                .queryParam("query", params.getQuery())
                .queryParam("maxResults", params.getMaxResults())
                .queryParam("sort", params.getSort())
                .queryParam("cover", params.getCoverSize())
                .queryParam("output", params.getOutput());

        System.out.println("uriComponentsBuilder = " + uriComponentsBuilder);

        String url = uriComponentsBuilder.toUriString();
        System.out.println("url = " + url);


        // Make the GET request and retrieve the response
        ResponseEntity<BookSearchedResult[]> response = restTemplate.getForEntity(url, BookSearchedResult[].class);
        BookSearchedResult[] results = response.getBody();

        // Return the results as a list
        return Arrays.asList(results);
    }

    public List<BookSearchedResult> searchResultByXml(BookSearchQuery query) throws ParserConfigurationException {

        ArrayList<BookSearchedResult> books = new ArrayList<>();

        BookSearchQueryParams params = new BookSearchQueryParams();
        params.setQuery(query.getQuery());
        System.out.println("params = " + params);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setCoalescing(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx")
                .queryParam("ttbkey", params.getKey())
                .queryParam("query", params.getQuery())
                .queryParam("maxResults", params.getMaxResults())
                .queryParam("sort", params.getSort())
                .queryParam("cover", params.getCoverSize())
                .queryParam("output", params.getOutput());

        System.out.println("uriComponentsBuilder = " + uriComponentsBuilder);

        String url = uriComponentsBuilder.toUriString();
        System.out.println("url = " + url);

        try {

            Document document = builder.parse(new URL(url).openStream());
            Element rootElement = document.getDocumentElement();
            // Do something with the root element and its children
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }


            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = fact.newDocumentBuilder();
            Document doc = builder.parse(url)

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("item");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                BookSearchedResult book = new BookSearchedResult();

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    book.setTitle(eElement.getElementsByTagName("title").item(temp).getTextContent());
                    book.setLink(eElement.getElementsByTagName("link").item(temp).getTextContent());
                    book.setPubDate(eElement.getElementsByTagName("pubDate").item(temp).getTextContent());
                    book.setAuthor(eElement.getElementsByTagName("author").item(temp).getTextContent());
                    book.setIsbn(eElement.getElementsByTagName("isbn").item(temp).getTextContent());
                    book.setPriceSales(eElement.getElementsByTagName("priceSales").item(temp).getTextContent());
                    book.setCategoryId(eElement.getElementsByTagName("categoryId").item(temp).getTextContent());
                    book.setCategoryName(eElement.getElementsByTagName("categoryName").item(temp).getTextContent());
                    book.setPublisher(eElement.getElementsByTagName("publisher").item(temp).getTextContent());

                    System.out.println("book = " + book);

                    books.add(book);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return books;

    }

    /*

    public BookSearchedResult parseResult(String jsonString) {
        BookSearchedResult result = new BookSearchedResult();
        try {
            JSONObject json = new JSONObject(jsonString);
            result.setTitle(json.getString("title"));
            result.setLink(json.getString("link"));
            result.setAuthor(json.getString("author"));
            result.setPubDate(json.getString("pubDate"));
            result.setIsbn(json.getString("isbn"));
            result.setCategoryId(json.getString("categoryId"));
            result.setCategoryName(json.getString("categoryName"));
            result.setPublisher(json.getString("publisher"));
            result.setPriceSales(json.getString("priceSales"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }*/

}