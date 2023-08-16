/**
 * @file playback-rate-menu-button.js
 */
import MenuButton from '../../menu/menu-button.js';
import PlaybackRateMenuItem from './playback-rate-menu-item.js';
import Component from '../../component.js';
import * as Dom from '../../utils/dom.js';

/**
 * The component for controlling the playback rate.
 *
 * @extends MenuButton
 */
class PlaybackRateMenuButton extends MenuButton {

  /**
   * Creates an instance of this class.
   *
   * @param { import('../../player').default } player
   *        The `Player` that this class should be attached to.
   *
   * @param {Object} [options]
   *        The key/value store of player options.
   */
  constructor(player, options) {
    super(player, options);

    this.menuButton_.el_.setAttribute('aria-describedby', this.labelElId_);

    this.updateVisibility();
    this.updateLabel();

    this.on(player, 'loadstart', (e) => this.updateVisibility(e));
    this.on(player, 'ratechange', (e) => this.updateLabel(e));
    this.on(player, 'playbackrateschange', (e) => this.handlePlaybackRateschange(e));
  }

  /**
   * Create the `Component`'s DOM element
   *
   * @return {Element}
   *         The element that was created.
   */
  createEl() {
    const el = super.createEl();

    this.labelElId_ = 'vjs-playback-rate-value-label-' + this.id_;

    this.labelEl_ = Dom.createEl('div', {
      className: 'vjs-playback-rate-value',
      id: this.labelElId_,
      textContent: '1x'
    });

    el.appendChild(this.labelEl_);

    return el;
  }

  dispose() {
    this.labelEl_ = null;

    super.dispose();
  }

  /**
   * Builds the default DOM `className`.
   *
   * @return {string}
   *         The DOM `className` for this object.
   */
  buildCSSClass() {
    return `vjs-playback-rate ${super.buildCSSClass()}`;
  }

  buildWrapperCSSClass() {
    return `vjs-playback-rate ${super.buildWrapperCSSClass()}`;
  }

  /**
   * Create the list of menu items. Specific to each subclass.
   *
   */
  createItems() {
    const rates = this.playbackRates();
    const items = [];

    for (let i = rates.length - 1; i >= 0; i--) {
      items.push(new PlaybackRateMenuItem(this.player(), {rate: rates[i] + 'x'}));
    }

    return items;
  }

  /**
   * On playbackrateschange, update the menu to account for the new items.
   *
   * @listens Player#playbackrateschange
   */
  handlePlaybackRateschange(event) {
    this.update();
  }

  /**
   * Get possible playback rates
   *
   * @return {Array}
   *         All possible playback rates
   */
  playbackRates() {
    const player = this.player();

    return (player.playbackRates && player.playbackRates()) || [];
  }

  /**
   * Get whether playback rates is supported by the tech
   * and an array of playback rates exists
   *
   * @return {boolean}
   *         Whether changing playback rate is supported
   */
  playbackRateSupported() {
    return this.player().tech_ &&
      this.player().tech_.featuresPlaybackRate &&
      this.playbackRates() &&
      this.playbackRates().length > 0
    ;
  }

  /**
   * Hide playback rate controls when they're no playback rate options to select
   *
   * @param {Event} [event]
   *        The event that caused this function to run.
   *
   * @listens Player#loadstart
   */
  updateVisibility(event) {
    if (this.playbackRateSupported()) {
      this.removeClass('vjs-hidden');
    } else {
      this.addClass('vjs-hidden');
    }
  }

  /**
   * Update button label when rate changed
   *
   * @param {Event} [event]
   *        The event that caused this function to run.
   *
   * @listens Player#ratechange
   */
  updateLabel(event) {
    if (this.playbackRateSupported()) {
      this.labelEl_.textContent = this.player().playbackRate() + 'x';
    }
  }

}

/**
 * The text that should display over the `PlaybackRateMenuButton`s controls.
 *
 * Added for localization.
 *
 * @type {string}
 * @protected
 */
PlaybackRateMenuButton.prototype.controlText_ = 'Playback Rate';

Component.registerComponent('PlaybackRateMenuButton', PlaybackRateMenuButton);
export default PlaybackRateMenuButton;
