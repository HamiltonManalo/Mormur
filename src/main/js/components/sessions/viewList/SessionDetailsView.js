import React from 'react'
import ReactDOM from 'react-dom'
import Question from "./questionComponent";

export default class SessionDetailsView extends React.PureComponent {
    constructor(props) {
        super(props);
        this.state = {
            questions: [],
            title: "",
            questionTextValue: ""
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
        // console.log(this.props.location)
        return(
            <div className="listview">
            <h2>{this.props.location.state ? this.props.location.state.entity.title : this.state.title}</h2>
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

        let sessionId = this.props.match.params.id
        fetch(`http://localhost:8080/api/sessionDetailses/${sessionId}/questions`).then(results =>{
            return results.json();
        }).then(data => {
            let questionsArray = data._embedded.questions.map(question => {
                return({
                        user: question._links.participant.href,
                        self: question._links.self.href,
                        text: question.questionText,
                        hashtags: question.hashTags,
                        sessionId: sessionId
                    })
            });
            this.setState({questions: questionsArray});
            // console.log("Question array ");
            // console.dir(questionsArray);
        });
        if(!this.props.location.entity) {
            fetch(`http://localhost:8080/api/sessionDetailses/${sessionId}`).then(results => results.json()).then(data => {
                this.setState({title: data.title})
            })
        }
    }

   

    get QuestionForm() {
        return(
            <div>
            <h3>Got a question?</h3>
            <form>
            <p><input type="text" value={this.state.questionTextValue} onChange={evt => this.UpdateValue(evt)} ref="questiontext" placeholder="ask a question..."/> </p> 
            <button className="submit" onClick={this.SubmitQuestion}>Submit</button>
            </form> 
            </div>
        )
    }
    UpdateValue(evt) {
        this.setState({questionTextValue: evt.target.value})
    }
    SubmitQuestion(){
        let text = this.state.questionTextValue
        if(text.length < 1) return;
        console.log("event value " + text)
        let bodyObject = {
            participantId: 1,
            questionText: text,
            hashTags: ["#firstSubmit!"],
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
        fetch("http://localhost:8080/api/session/questions", options)
        .then(response => response.json())
        .then(data => {console.dir(data)});
    }

}