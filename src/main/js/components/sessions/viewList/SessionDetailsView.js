import React from 'react'
import Question from "./question";

export default class SessionDetailsView extends React.PureComponent {
    constructor(props) {
        super(props)
        this.state = {
            questions: []
        }
    }

    componentDidMount() {

        let location = this.props.location.pathname.split("/");
        let sessionId = location[location.length - 1];
        fetch(`http://localhost:8080/api/sessionDetailses/${sessionId}/questions`).then(results =>{
            return results.json()
        }).then(data => {
            let questionsArray = data._embedded.questions.map(question => {
                return({
                        user: question._links.participant.href,
                        self: question._links.self.href,
                        text: question.questionText,
                        sessionId: sessionId
                    })
            });
            this.setState({questions: questionsArray});
            console.log("Question array ")
            console.dir(questionsArray)
        })
    }

    render() {
        // console.log("render called");
        let sessionQuestions = this.state.questions.map(question =>
            <Question key={question.self}
                      userId={question.user}
                      text={question.text}
                      answer={false}/>
        );
        console.log("session questions")
        console.log(sessionQuestions)
        return(
            <div>
                <p> something</p>
                <tr>
                {sessionQuestions}
                </tr>
            </div>
        );
    }
}