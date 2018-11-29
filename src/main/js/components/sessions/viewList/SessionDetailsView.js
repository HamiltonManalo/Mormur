import React, {Fragment} from 'react'
import Question from "./questionComponent";

export default class SessionDetailsView extends React.PureComponent {
    constructor(props) {
        super(props);
        // console.dir(props);
        this.state = {
            questions: [],
            title: "",
            questionTextValue: "",
            questionHashTags: ""
        }
        this.SubmitQuestion = this.SubmitQuestion.bind(this);
    }
    
    render() {
        let sessionQuestions = this.state.questions.map(question =>
            <Question key={question.self}
                      userId={question.user}
                      text={question.text}
                      hashtags={question.hashtags}
                      answer={false}/>
        );
        // console.log("session questions")
        // console.log(sessionQuestions)
        return(
            <div className="maincontainer listview">
                <div>
                <h2>{this.props.location.state ? this.props.location.state.entity.title : this.state.title}</h2>
                <h4>Hosted By: {this.props.match.params.id} </h4> 
                </div>
                {this.QuestionForm}
                <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Question</th>
                        <th>Hash Tags</th>
                        <th>Answer</th>
                    </tr>
                </thead>
                    <tbody>
                        {sessionQuestions}
                    </tbody>
                </table>
            </div>
        );
    }
    componentDidMount() {

        fetch(`/../../api/sessionDetailses/${this.props.match.params.id}/questions`).then(results =>{
            return results.json();
        }).then(data => {
            let questionsArray = data._embedded.questions.map(question => {
                return({
                        user: question._links.participant.href,
                        self: question._links.self.href,
                        text: question.questionText,
                        hashtags: question.hashTags,
                        sessionId: this.props.match.params.id
                    })
            });
            this.setState({questions: questionsArray});
            // console.log("Question array ");
            // console.dir(questionsArray);
        });
        if(!this.props.location.entity) {
            fetch(`/../../api/sessionDetailses/${this.props.match.params.id}`).then(results => results.json()).then(data => {
                this.setState({title: data.title})
            })
        }
    }

   

    get QuestionForm() {
        return(
            <Fragment>
            <h3>Got a question?</h3>
            <form>
            <p><input type="text" value={this.state.questionTextValue} onChange={evt => this.UpdateQuestionValue(evt)} ref="questiontext" placeholder="ask a question..."/> </p> 
            <p><input type="text" value={this.state.questionHashTags} onChange={evt => this.UpdateHashTagValue(evt)} ref="hashtagtext" placeholder="Add some tags!"/> </p> 
            <button className="submit" onClick={this.SubmitQuestion}>Submit</button>
            </form> 
            </Fragment>
        )
    }
    UpdateQuestionValue(evt) {
        this.setState({questionTextValue: evt.target.value})
    }
    UpdateHashTagValue(evt) {
        this.setState({questionHashTags: evt.target.value})
    }
    SubmitQuestion(){
        let text = this.state.questionTextValue;
        //Logic for split needs to be improved for edge cases like #yolo#hashtag
        let hashtags = this.state.questionHashTags.split(" ");
        if(text.length < 1) return;
        console.log("event value " + text)
        let bodyObject = {
            participantId: 1,
            questionText: text,
            hashTags: hashtags,
            sessionId: this.props.match.params.id
        }
        let options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            redirect: "follow",
            body: JSON.stringify(bodyObject)
        }
        fetch("/../../api/session/questions", options)
        .then(response => response.json())
        .then(data => {console.dir(data)});
    }
}