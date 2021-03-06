package WSDL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import Utilities.SetStringFieldBridge;
import WADL.Link;

@XmlRootElement
@Entity
@Table(name="outputParameter")
@Indexed
public class OutputParameterModel
{
	//properties

	@Transient
	private List<Link> linkList = new ArrayList<Link>();
	//place holder for all resource model properties

	@Id
	@GeneratedValue
	@Column(name = "outputParameterId")
	private int outputParameterId;

	@Column(name = "name")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String name;
	

	@Column(name = "searchOntology")
	private String searchOntology;
	
	@Column(name = "searchConcept")
	private String searchConcept;
	
	
	@Column(name = "type")
	private String type;
	
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="outputParameterKeyword", joinColumns=@JoinColumn(name="outputParameterId"))
    @ForeignKey(name = "fk_outputParameter_keyword")
	@Column(name = "keyword")
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@FieldBridge(impl=SetStringFieldBridge.class)
	private Set<String> keyword  = new HashSet<String>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="oOutputParameter",orphanRemoval=true)
    @OnDelete(action=OnDeleteAction.CASCADE)
	private Set<OutputParameterModel> setOfOutputParameter = new HashSet<OutputParameterModel>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="outputMessageId")
    @ForeignKey(name = "fk_outputMessage_outputParameter")
	private OutputMessageModel oOutputMessage;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="sourceOutputParameterId")
    @ForeignKey(name = "fk_outputParameter_outputParameter")
	private OutputParameterModel oOutputParameter;
	
	//operations
	//place holder for marshalModel operation

    public OutputParameterModel marshalModel(List<Link> linkList)
    {
        this.linkList = linkList;
        return this;
    }
	//place holder for setLinkList operation

    public void setLinkList(List<Link> linkList)
    {
        this.linkList = linkList;
    }
	//place holder for getLinkList operation

    public List<Link> getLinkList()
    {
        return linkList;
    }

	//place holder for all setters and getters of properties
	
	public int getOutputParameterId()
	{
		return outputParameterId;
	}
	
	public void setOutputParameterId(int outputParameterId)
	{
		this.outputParameterId = outputParameterId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setSearchOntology(String searchOntology)
	{
		this.searchOntology = searchOntology;
	}
	
	public String getSearchOntology()
	{
		return searchOntology;
	}
	
	public void setSearchConcept(String searchConcept)
	{
		this.searchConcept = searchConcept;
	}
	
	public String getSearchConcept()
	{
		return searchConcept;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public Set<String> getKeyword()
	{
		return keyword;
	}
	
	public void setKeyword(Set<String> keyword)
	{
		this.keyword = keyword;
	}

    public void deleteAllCollections(Session hibernateSession)
    {
        Query query = hibernateSession.createSQLQuery(String.format("DELETE FROM %s where %sId = %d","outputParameterKeyword".toLowerCase(),"outputParameter",this.getOutputParameterId()));
        query.executeUpdate();

        Iterator<OutputParameterModel> outputParameterIterator = setOfOutputParameter.iterator();
        while(outputParameterIterator.hasNext())
        {
            outputParameterIterator.next().deleteAllCollections(hibernateSession);
        }
    }
	
    @XmlTransient
    public Set<OutputParameterModel> getSetOfOutputParameter()
    {
        return this.setOfOutputParameter;
    }

    public void setSetOfOutputParameter( Set<OutputParameterModel> setOfOutputParameter)
    {
        this.setOfOutputParameter = setOfOutputParameter;
    }

    @XmlTransient
	public OutputMessageModel getOutputMessage()
	{
		return oOutputMessage;
	}
	
	public void setOutputMessage( OutputMessageModel oOutputMessage)
	{
		this.oOutputMessage = oOutputMessage;
	}
	
    @XmlTransient
	public OutputParameterModel getOutputParameter()
	{
		return oOutputParameter;
	}
	
	public void setOutputParameter( OutputParameterModel oOutputParameter)
	{
		this.oOutputParameter = oOutputParameter;
	}
	
}