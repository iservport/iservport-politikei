[#ftl][#include "lang.ftl" /]
<md-button class="md-primary md-ink-ripple" data-ng-if="vote.valid">
    <span class="md-caption"> ${label_you_voted} {{vote.vote}} </span>
</md-button>
<md-button class="md-primary md-ink-ripple" data-ng-if="!vote.valid" data-ng-click="voteAs(1)" >
    <span class="md-caption">
        ${label_votedUp} <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
    </span>
</md-button>
<md-button class="md-ink-ripple" data-ng-if="!vote.valid" data-ng-click="voteAs(0)" >
    <span class="md-caption">${label_votedOther}</span>
</md-button>
<md-button class="md-warn md-ink-ripple" data-ng-if="!vote.valid" data-ng-click="voteAs(-1)" >
    <span class="md-caption">
        ${label_votedDown} <i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
    </span>
</md-button>
