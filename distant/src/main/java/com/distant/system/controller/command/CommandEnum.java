package com.distant.system.controller.command;

import com.distant.system.controller.command.factory.impl.*;
import com.distant.system.controller.command.factory.impl.student.ListStudentSubjectCommand;
import com.distant.system.controller.command.factory.impl.student.StudentExamCommand;
import com.distant.system.controller.command.factory.impl.student.StudentExamListCommand;
import com.distant.system.controller.command.factory.impl.student.StudentExamResultCommand;
import com.distant.system.controller.command.factory.impl.teacher.*;

/**
 * A enum for creating Commands that process the request commands
 */
public enum CommandEnum {

    LOGIN {
        {
            this.command = new LogInCommand();
        }
    },

    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },

    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },

    DELETESUBJECT {
        {
            this.command = new DeleteSubjectCommand();
        }
    },
    EDITSUBJECT {
        {
            this.command = new EditSubjectCommand();
        }
    },
    STUDENTEXAM {
        {
            this.command = new StudentExamCommand();
        }
    },
    EXAMANSWER {
        {
            this.command = new StudentExamResultCommand();
        }
    },
    ADDSUBJECT {
        {
            this.command = new AddSubjectCommand();
        }
    },
    ADDQUESTION {
        {
            this.command = new AddQuestionCommand();
        }
    },
    UPDATE_QUESTION {
        {
            this.command = new UpdateQuestionCommand();
        }
    },
    RESULTS {
        {
            this.command = new ExamResultsCommand();
        }
    },
    SUBJECTS {
        {
            this.command = new ListSubjectsCommand();
        }
    },
    QUESTIONS {
        {
            this.command = new ListQuestionsCommand();
        }
    },
    SUBJECT_LIST {
        {
            this.command = new ListStudentSubjectCommand();
        }
    },
    EXAM_LIST {
        {
            this.command = new StudentExamListCommand();
        }
    },
    DELETE_EXAM_RESULT {
        {
            this.command = new DeleteExamResultCommand();
        }
    },
    DELETE_QUESTION {
        {
            this.command = new DeleteQuestionCommand();
        }
    },
    OPEN_ADD_QUESTION {
        {
            this.command = new OpenAddQuestionCommand();
        }
    },
    OPEN_UPDATE_QUESTION {
        {
            this.command = new OpenUpdateQuestionCommand();
        }
    },
    OPEN_EDIT_SUBJECT {
        {
            this.command = new OpenEditSubjectCommand();
        }
    };


    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
