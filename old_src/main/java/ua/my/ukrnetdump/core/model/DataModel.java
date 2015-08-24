package ua.my.ukrnetdump.core.model;

/**
 * Created by pavel-pc on 16.07.15.
 */
public class DataModel {

    final private String tagNews;
    final private String titleNews;
    final private String titleNewsUrl;
    final private String dataNews;
    final private String newsText;

    public DataModel(String tagNews, String titleNews, String titleNewsUrl, String dataNews, String newsText) {
        this.tagNews = tagNews;
        this.titleNews = titleNews;
        this.titleNewsUrl = titleNewsUrl;
        this.dataNews = dataNews;
        this.newsText = newsText;
    }

    public String getTagNews() {
        return tagNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public String getTitleNewsUrl() {
        return titleNewsUrl;
    }

    public String getDataNews() {
        return dataNews;
    }

    public String getNewsText() {
        return newsText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataModel dataModel = (DataModel) o;

        if (tagNews != null ? !tagNews.equals(dataModel.tagNews) : dataModel.tagNews != null) return false;
        if (titleNews != null ? !titleNews.equals(dataModel.titleNews) : dataModel.titleNews != null) return false;
        if (titleNewsUrl != null ? !titleNewsUrl.equals(dataModel.titleNewsUrl) : dataModel.titleNewsUrl != null)
            return false;
        if (dataNews != null ? !dataNews.equals(dataModel.dataNews) : dataModel.dataNews != null) return false;
        return !(newsText != null ? !newsText.equals(dataModel.newsText) : dataModel.newsText != null);

    }

    @Override
    public int hashCode() {
        int result = tagNews != null ? tagNews.hashCode() : 0;
        result = 31 * result + (titleNews != null ? titleNews.hashCode() : 0);
        result = 31 * result + (titleNewsUrl != null ? titleNewsUrl.hashCode() : 0);
        result = 31 * result + (dataNews != null ? dataNews.hashCode() : 0);
        result = 31 * result + (newsText != null ? newsText.hashCode() : 0);
        return result;
    }
}